// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import java.util.function.Predicate;

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
  private final String format;
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
   * @param format
   *          message format describing the failing limit check.<br>
   *          parameters:
   *          <ul>
   *          <li>%1: name</li>
   *          <li>%2: value when performing {@link #check(value)}</li>
   *          <li>%3: limit</li>
   *          </ul>
   */
  public Limit(final String name, final LIMIT limit, final Predicate<VALUE> predicate, final String format) {
    this.name = name;
    this.limit = limit;
    this.predicate = predicate;
    this.format = format;
  }

  /**
   * Check value.<br>
   *
   * @return Message when the value exceeds the limit, or <code>null</code>.
   */
  public String check(final VALUE value) {
    if (!predicate.test(value)) {
      return String.format(format, name, value, limit);
    }
    return null;
  }
}
