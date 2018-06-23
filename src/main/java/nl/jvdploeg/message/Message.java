// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Map;

/**
 * Represents a message in a locale independent manner.
 * <p>
 * The message key can be used to lookup the message definition in a (locale
 * specific) bundle. Or the message key itself can be used as the message
 * definition.
 * <p>
 * The message definition can refer to values present in the message argument
 * map by using the following notation:
 * <ul>
 * <li>Direct<br>
 * Enclose a key in curly braces {...}<br>
 * Find the value in the argument map.<br>
 * And optionally defines how the value must be formatted. See
 * {@link String#format(String, Object...) String.format} for format options.
 * <li>Indirect<br>
 * Enclose a key in square brackets [...]<br>
 * Find the value in the argument map, and perform a lookup with this value in
 * the message bundle.<br>
 * Because the value is looked up and already localized there is no need to
 * specify a format.
 * </ul>
 * In order to be able to use curly braces or square brackets litterally in the
 * message use the character itself as escape code.<br>
 * <p>
 * <b>Examples</b><br>
 * <ul>
 * <li>Message definition in bundle<br>
 * <ul>
 * <li>Direct<br>
 * Message key: open.door.number<br>
 * Bundle content: open.door.number=Open door number {doorNumber}.<br>
 * Message argument: doorNumber=2 (a Number)<br>
 * Result: Open door number 2.
 * <li>Indirect<br>
 * Message key: label.color<br>
 * Message argument: color=color.green (a String)<br>
 * Bundle content: label.color=Kleur: [color]<br>
 * Bundle content: color.green=groen<br>
 * Result: Kleur: groen
 * <li>Escape codes<br>
 * Message key: message.containing.brackets<br>
 * Bundle content: message.containing.brackets=Message containing [[, ]], {{,
 * and }}.<br>
 * Result: Message containing [, ], {, and }.
 * <li>Direct and formatted<br>
 * Message key: label.weight<br>
 * Bundle content: label.weight=Weight: {weight,%4.2f} [[g]]<br>
 * Message argument: weight=3.33333 (a Double)<br>
 * Message: Weight: 3.33 [g]
 * </ul>
 * <li>Message definition without bundle<br>
 * <ul>
 * <li>Direct<br>
 * Message key: Open door number {doorNumber}.<br>
 * Message argument: doorNumber=2 (a Number)<br>
 * Result: Open door number 2.<br>
 * </ul>
 * </ul>
 */
public interface Message {

  /**
   * Get message arguments.<br>
   * Example: doorNumber:2<br>
   * Example: color:colorGreen
   */
  Map<String, Object> getArguments();

  /**
   * Get message key.<br>
   * Example: open.door.number<br>
   * Example: the.color.door
   */
  String getKey();
}
