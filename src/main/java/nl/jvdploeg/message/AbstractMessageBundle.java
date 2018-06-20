// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.jvdploeg.regex.Regex;

/**
 * Message bundle containing translatable messages.<br>
 * <b>Example</b><br>
 * Message format:
 *
 * <pre>
 * color.green=green
 * text.with.litterals=Text with [propertyColor] and {doorNumber} and literally [[x]], {{y}}.
 * </pre>
 *
 * Message:
 *
 * <pre>
 * key=text.with.litterals
 * arguments=[propertyColor=color.green, doorNumber=2]
 * </pre>
 *
 * Translate message result:
 *
 * <pre>
 * Text with green and 2 and letterlijk [x], {y}.
 * </pre>
 */

public abstract class AbstractMessageBundle implements MessageBundle {

  /** Argument begin marker. */
  private static final String ARGUMENT_BEGIN = "{";
  /** Argument end marker. */
  private static final String ARGUMENT_END = "}";
  /** Indirection begin marker. */
  private static final String INDIRECTION_BEGIN = "[";
  /** Indirection end marker. */
  private static final String INDIRECTION_END = "]";

  /** Argument begin marker, used in regex. */
  private static final String ESCAPED_ARGUMENT_BEGIN = "\\{";
  /** Argument end marker, used in regex. */
  private static final String ESCAPED_ARGUMENT_END = "\\}";
  /** Indirection begin marker, used in regex. */
  private static final String ESCAPED_INDIRECTION_BEGIN = "\\[";
  /** Indirection end marker, used in regex. */
  private static final String ESCAPED_INDIRECTION_END = "\\]";

  /** Pre-compiled litteral argument begin pattern. */
  private static final Pattern LITTERAL_ARGUMENT_BEGIN_PATTERN = Pattern.compile(ESCAPED_ARGUMENT_BEGIN + ESCAPED_ARGUMENT_BEGIN);
  /** Pre-compiled litteral argument end pattern. */
  private static final Pattern LITTERAL_ARGUMENT_END_PATTERN = Pattern.compile(ESCAPED_ARGUMENT_END + ESCAPED_ARGUMENT_END);
  /** Pre-compiled litteral indirection begin pattern. */
  private static final Pattern LITTERAL_INDIRECTION_BEGIN_PATTERN = Pattern.compile(ESCAPED_INDIRECTION_BEGIN + ESCAPED_INDIRECTION_BEGIN);
  /** Pre-compiled litteral indirection end pattern. */
  private static final Pattern LITTERAL_INDIRECTION_END_PATTERN = Pattern.compile(ESCAPED_INDIRECTION_END + ESCAPED_INDIRECTION_END);

  /** Argument name pattern. */
  private static final String ARGUMENT_NAME_PATTERN = //
      Regex.oneOrMore(Regex.or(Regex.not(ESCAPED_ARGUMENT_BEGIN + ESCAPED_ARGUMENT_END)));
  /** Argument pattern format. */
  private static final String ARGUMENT_PATTERN_FORMAT = //
      Regex.negativeLookbehind(ESCAPED_ARGUMENT_BEGIN) //
          + curlyBrackets("%s") //
          + Regex.negativeLookahead(ESCAPED_ARGUMENT_END);
  /** Pre-compiled argument pattern. */
  private static final Pattern ARGUMENT_PATTERN = //
      Pattern.compile(String.format(ARGUMENT_PATTERN_FORMAT, //
          Regex.group(ARGUMENT_NAME_PATTERN)));
  /** Indirection name pattern. */
  private static final String INDIRECTION_NAME_PATTERN = //
      Regex.oneOrMore(Regex.or(Regex.not(ESCAPED_INDIRECTION_BEGIN + ESCAPED_INDIRECTION_END)));
  /** Indirection pattern format. */
  private static final String INDIRECTION_PATTERN_FORMAT = //
      Regex.negativeLookbehind(ESCAPED_INDIRECTION_BEGIN) //
          + squareBrackets("%s") //
          + Regex.negativeLookahead(ESCAPED_INDIRECTION_END);
  /** Pre-compiled indirection pattern. */
  private static final Pattern INDIRECTION_PATTERN = //
      Pattern.compile(String.format(INDIRECTION_PATTERN_FORMAT, //
          Regex.group(INDIRECTION_NAME_PATTERN)));

  /**
   * Translate message.
   */
  @Override
  public final String translate(final Message message) {
    // unpack message
    final String key = message.getKey();
    final Map<String, String> arguments = message.getArguments();
    // start translating
    String intermediate = getString(key);
    // lookup arguments
    final List<String> argumentKeys = find(ARGUMENT_PATTERN, intermediate);
    if (argumentKeys.size() > 0) {
      for (final String argumentKey : argumentKeys) {
        final String argumentValue = arguments.get(argumentKey);
        if (argumentValue == null) {
          throw new IllegalStateException("message missing argument value, message:" + key + ", argument:" + argumentKey);
        }
        intermediate = replaceAll(intermediate, argumentPattern(argumentKey), argumentValue);
      }
    }
    // double lookup indirections
    final List<String> indirectionKeys = find(INDIRECTION_PATTERN, intermediate);
    for (final String indirectionKey : indirectionKeys) {
      final String indirectionValue = arguments.get(indirectionKey);
      if (indirectionValue == null) {
        throw new IllegalStateException("message missing indirection value, message:" + key + ", indirection:" + indirectionKey);
      }
      final String argumentValue = getString(indirectionValue);
      intermediate = replaceAll(intermediate, indirectionPattern(indirectionKey), argumentValue);
    }
    // handle litteral argument and indirection markers
    intermediate = replaceAll(intermediate, LITTERAL_INDIRECTION_BEGIN_PATTERN, INDIRECTION_BEGIN);
    intermediate = replaceAll(intermediate, LITTERAL_INDIRECTION_END_PATTERN, INDIRECTION_END);
    intermediate = replaceAll(intermediate, LITTERAL_ARGUMENT_BEGIN_PATTERN, ARGUMENT_BEGIN);
    intermediate = replaceAll(intermediate, LITTERAL_ARGUMENT_END_PATTERN, ARGUMENT_END);
    return intermediate;
  }

  /**
   * Create argument pattern.
   */
  private static String argumentPattern(final String argumentKey) {
    return String.format(ARGUMENT_PATTERN_FORMAT, argumentKey);
  }

  /**
   * Enclose in curly brackets.
   */
  private static String curlyBrackets(final String string) {
    return ESCAPED_ARGUMENT_BEGIN + string + ESCAPED_ARGUMENT_END;
  }

  /**
   * Find pattern in input.
   */
  private static List<String> find(final Pattern pattern, final String input) {
    final List<String> matches = new ArrayList<>();
    final Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      final String group = matcher.group(1);
      matches.add(group);
    }
    return matches;
  }

  /**
   * Create indirection pattern.
   */
  private static String indirectionPattern(final String indirection) {
    return String.format(INDIRECTION_PATTERN_FORMAT, indirection);
  }

  /**
   * Replace all.
   */
  private static String replaceAll(final String input, final Pattern pattern, final String replacement) {
    return pattern.matcher(input).replaceAll(replacement);
  }

  /**
   * Replace all.
   */
  private static String replaceAll(final String input, final String regex, final String replacement) {
    return Pattern.compile(regex).matcher(input).replaceAll(replacement);
  }

  /**
   * Enclose in square brackets.
   */
  private static String squareBrackets(final String string) {
    return ESCAPED_INDIRECTION_BEGIN + string + ESCAPED_INDIRECTION_END;
  }

  /**
   * Constructor.
   */
  protected AbstractMessageBundle() {
  }

  protected abstract String getString(String key);
}
