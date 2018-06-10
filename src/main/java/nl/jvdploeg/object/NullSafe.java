// The author disclaims copyright to this source code.
package nl.jvdploeg.object;

import java.util.function.Function;

public abstract class NullSafe {

  /**
   * Compare objects of a {@link Comparable} type.<br>
   * Where <code>null</code> is less than any non-null object.
   */
  public static <T> int compareTo(final Comparable<T> one, final T other) {
    if (one == other) {
      return 0;
    }
    if (one == null) {
      return -1;
    }
    if (other == null) {
      return 1;
    }
    return one.compareTo(other);
  }

  public static boolean equals(final Object one, final Object other) {
    if (one == null) {
      return other == null;
    }
    return one.equals(other);
  }

  public static <T, R> R function(final T input, final Function<T, R> function) {
    if (input == null) {
      return null;
    }
    return function.apply(input);
  }

  public static int hashCode(final Object object) {
    if (object == null) {
      return 0;
    }
    return object.hashCode();
  }

  public static String toString(final Object value) {
    return value == null ? "null" : value.toString();
  }

  private NullSafe() {
  }
}
