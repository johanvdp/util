// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.jvdploeg.object.NullSafe;
import nl.jvdploeg.regex.Regex;

/**
 * Message bundle performs {@link Message} translation.
 */
public abstract class AbstractMessageBundle implements MessageBundle {

  private static final class Argument {

    /**
     * Create argument from definition.<br>
     * Format: &lt;name&gt;[,&lt;format&gt;]<br>
     * Example: count<br>
     * Example: weight,%4.2f
     */
    public static Argument create(final String definition) {
      final int indexOfSeparator = definition.indexOf(",");
      final String argumentName;
      final String argumentFormat;
      if (indexOfSeparator == -1) {
        argumentName = definition;
        argumentFormat = null;
      } else {
        argumentName = definition.substring(0, indexOfSeparator);
        argumentFormat = definition.substring(indexOfSeparator + 1);
      }
      return new Argument(definition, argumentName, argumentFormat);
    }

    private final String definition;
    private final String key;

    private final String format;

    private Argument(final String definition, final String key, final String format) {
      this.definition = definition;
      this.key = key;
      this.format = format;
    }
  }

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

  /**
   * Translate messages.
   */
  public final String[] translate(final Message... messages) {
    final List<String> translated = new ArrayList<>();
    if (messages != null) {
      for (final Message message : messages) {
        final String one = translate(message);
        translated.add(one);
      }
    }
    final String[] array = translated.toArray(new String[translated.size()]);
    return array;
  }

  /**
   * Translate message.
   */
  @Override
  public final String translate(final Message message) {
    // unpack message
    final String messageKey = message.getKey();
    final Map<String, Object> messageArgumentValues = message.getArguments();
    // start translating
    String intermediate = getString(message);
    // lookup arguments
    final List<Argument> arguments = findArguments(intermediate);
    if (arguments.size() > 0) {
      for (final Argument argument : arguments) {
        final Object argumentValue = messageArgumentValues.get(argument.key);
        if (argumentValue == null) {
          throw new IllegalStateException(String.format("message missing argument value, message: %s, argument: %s", messageKey, argument.key));
        }
        // format argument value
        final String formattedArgumentValue = format(argument, argumentValue);
        intermediate = replaceAll(intermediate, argumentPattern(argument.definition), formattedArgumentValue);
      }
    }
    // double lookup indirections
    final List<String> indirectionKeys = find(INDIRECTION_PATTERN, intermediate);
    for (final String indirectionKey : indirectionKeys) {
      final Object indirectionValue = messageArgumentValues.get(indirectionKey);
      if (indirectionValue instanceof String) {
        // replace valid indirections
        final String argumentValue = getString((String) indirectionValue);
        intermediate = replaceAll(intermediate, indirectionPattern(indirectionKey), argumentValue);
      }
    }
    // handle litteral argument and indirection markers
    intermediate = replaceAll(intermediate, LITTERAL_INDIRECTION_BEGIN_PATTERN, INDIRECTION_BEGIN);
    intermediate = replaceAll(intermediate, LITTERAL_INDIRECTION_END_PATTERN, INDIRECTION_END);
    intermediate = replaceAll(intermediate, LITTERAL_ARGUMENT_BEGIN_PATTERN, ARGUMENT_BEGIN);
    intermediate = replaceAll(intermediate, LITTERAL_ARGUMENT_END_PATTERN, ARGUMENT_END);
    return intermediate;
  }

  private List<Argument> createArguments(final List<String> argumentDefinitions) {
    final List<Argument> arguments = new ArrayList<>();
    for (final String argumentDefinition : argumentDefinitions) {
      arguments.add(Argument.create(argumentDefinition));
    }
    return arguments;
  }

  private List<Argument> findArguments(final String message) {
    final List<String> definitions = find(ARGUMENT_PATTERN, message);
    final List<Argument> arguments = createArguments(definitions);
    return arguments;
  }

  private String format(final Argument argument, final Object value) {
    if (argument.format == null) {
      return NullSafe.toString(value);
    }
    final Locale locale = getLocale();
    return String.format(locale, argument.format, value);
  }

  /** Get the locale of this message bundle. */
  protected abstract Locale getLocale();

  /**
   * Get a string value in this message bundle.
   *
   * @param message
   *          The message to lookup.
   */
  protected abstract String getString(Message message);

  /**
   * Get a string value in this message bundle.
   *
   * @param key
   *          The key to lookup.
   */
  protected abstract String getString(String key);
}
