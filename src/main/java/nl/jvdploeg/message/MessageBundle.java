// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

/** A message bundle can perform {@link Message} translation. */
public interface MessageBundle {

  String translate(Message message);
}
