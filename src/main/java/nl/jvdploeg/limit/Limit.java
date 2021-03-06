// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import java.util.function.Predicate;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageDefinition;
import nl.jvdploeg.object.NullSafe;

/**
 * Argument limit check.<br>
 *
 * @param VALUE
 *          value type
 * @param LIMIT
 *          limit type
 */
public final class Limit<VALUE, LIMIT> {

  private final LIMIT limit;
  private final Predicate<VALUE> predicate;
  private final MessageDefinition definition;
  private final String name;

  /**
   * Constructor.<br>
   *
   * @param name
   *          argument name
   * @param limit
   *          argument limit
   * @param predicate
   *          argument check function
   * @param definition
   *          message describing the failing limit check.<br>
   *          parameters:
   *          <ul>
   *          <li>{name}: name</li>
   *          <li>{value}: value when performing {@link #check(value)}</li>
   *          <li>{limit}: limit</li>
   *          </ul>
   */
  public Limit(final String name, final LIMIT limit, final Predicate<VALUE> predicate, final MessageDefinition definition) {
    this.name = name;
    this.limit = limit;
    this.predicate = predicate;
    this.definition = definition;
  }

  /**
   * Check value.<br>
   *
   * @return Message when the value exceeds the limit, or <code>null</code>.
   */
  public Message check(final VALUE value) {
    if (!predicate.test(value)) {
      final Message message = new MessageBuilder(definition) //
          .add("name", name) //
          .add("value", NullSafe.toString(value)) //
          .add("limit", NullSafe.toString(limit)) //
          .build();
      return message;
    }
    return null;
  }
}
