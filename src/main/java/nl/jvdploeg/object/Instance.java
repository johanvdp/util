// The author disclaims copyright to this source code.
package nl.jvdploeg.object;

public abstract class Instance {

  public static String identity(final Object object) {
    final StringBuilder builder = new StringBuilder();
    if (object == null) {
      builder.append("null");
    } else {
      builder.append(object.getClass().getSimpleName());
      builder.append("@");
      builder.append(System.identityHashCode(object));
    }
    return builder.toString();
  }

  private Instance() {
  }
}
