// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Map;

/**
 * Message definition.<br>
 * The message key is used to lookup the message format in the resource
 * bundle.<br>
 * The message format can contain marked fragments.<br>
 * Any {marked} fragment can be found in the argument map.<br>
 * Any [marked] fragment can be found in the argument map, and the value is an
 * indirection requiring lookup in the resource bundle.<br>
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
  Map<String, String> getArguments();
}
