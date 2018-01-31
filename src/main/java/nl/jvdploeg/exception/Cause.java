// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class Cause {

  public static String getCause(final Throwable t) {
    final StringBuilder builder = new StringBuilder();
    // avoid recursion collect visited throwables
    final List<Throwable> visited = new ArrayList<>();
    recurse(visited, builder, t);
    final String causes = builder.toString();
    return causes;
  }

  /**
   * Recurse throwable causes and build a concatenated cause message.<br>
   *
   * @return A semi-colon separated list of throwable cause messages.
   */
  private static void recurse(final List<Throwable> visited, final StringBuilder builder, final Throwable t) {
    if (t != null) {
      if (!visited.contains(t)) {
        visited.add(t);
        final String message = t.getMessage();
        if (message != null) {
          if (builder.length() > 0) {
            builder.append("; ");
          }
          builder.append(message.trim());
        }
        final Throwable cause = t.getCause();
        if (cause != null) {
          recurse(visited, builder, cause);
        }
      }
    }
  }
}
