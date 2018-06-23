// The author disclaims copyright to this source code.
package nl.jvdploeg.util;

import java.util.Set;
import java.util.TreeSet;

public abstract class Arrays {

  private Arrays() {
  }

  /**
   * Convert array into a set.
   *
   * @throws IllegalArgumentException
   *           when the array contains duplicate elements
   *           ({@link #equals(Object)}).
   */
  @SafeVarargs
  public static <T> Set<T> asSet(final T... array) {
    final Set<T> set = new TreeSet<>();
    if (array != null) {
      for (final T element : array) {
        final boolean added = set.add(element);
        if (!added) {
          throw new IllegalArgumentException(String.format("duplicate element: %s", element));
        }
      }
    }
    return set;
  }
}
