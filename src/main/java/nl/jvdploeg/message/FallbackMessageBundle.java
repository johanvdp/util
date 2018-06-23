// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * MessageBundle that performs no translation.<br>
 * It shows the message key, the argument names and their value.
 */
public final class FallbackMessageBundle extends AbstractMessageBundle {

  /**
   * Fragment of argument name and argument value.<br>
   * Example: x={x}
   */
  private static String toString(final Entry<String, Object> argument) {
    final String argumentKey = argument.getKey();
    return String.format("%1$s={%1$s}", argumentKey);
  }

  /**
   * Fragment; comma separated sequence of arguments.<br>
   * Example: x={x}, y={y}
   */
  private static String toString(final Map<String, Object> arguments) {
    final StringBuilder builder = new StringBuilder();
    for (final Entry<String, Object> argument : arguments.entrySet()) {
      if (builder.length() > 0) {
        builder.append(", ");
      }
      builder.append(toString(argument));
    }
    return builder.toString();
  }

  /**
   * Constructor.
   */
  public FallbackMessageBundle() {
  }

  @Override
  protected Locale getLocale() {
    return Locale.getDefault();
  }

  /**
   * Build message showing the message key and, for all arguments, the argument
   * name and value.<br>
   * Example: some.message: x={x}, y={y}
   */
  @Override
  protected String getString(final Message message) {
    //
    final String messageKey = message.getKey();
    final Map<String, Object> arguments = message.getArguments();
    if (arguments.size() == 0) {
      // no arguments
      return messageKey;
    }
    final String argumentsAsString = toString(arguments);
    return String.format("%s: %s", messageKey, argumentsAsString);
  }

  @Override
  protected String getString(final String key) {
    return key;
  }
}
