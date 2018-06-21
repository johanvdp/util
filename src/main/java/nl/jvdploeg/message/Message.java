// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Map;

/**
 * Message definition.<br>
 * The message key is used to lookup the message in the message bundle.<br>
 * The message value can contain marked fragments.<br>
 * Curly braces {...} identify a name for which the value can be found in the
 * argument map.<br>
 * Square brackets [...] identify a name for which the value is an indirection
 * that requires a lookup in the message bundle.<br>
 * Use the backslash \ character as escape code.<br>
 * Example: Open door number {doorNumber}.<br>
 * Example: The [color] door.
 */
public interface Message {

  /**
   * Get message key.<br>
   * Example: open.door.number<br>
   * Example: the.color.door
   */
  String getKey();

  /**
   * Get message arguments.<br>
   * Example: doorNumber:2<br>
   * Example: color:colorGreen
   */
  Map<String, Object> getArguments();
}
