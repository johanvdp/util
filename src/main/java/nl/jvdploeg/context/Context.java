// The author disclaims copyright to this source code.
package nl.jvdploeg.context;

import nl.jvdploeg.exception.ErrorBuilder;
import nl.jvdploeg.exception.IllegalArgumentExceptionBuilder;
import nl.jvdploeg.exception.IllegalStateExceptionBuilder;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class Context<T> {

  /** The inner most context. */
  private static final ThreadLocal<Context> INNER = new ThreadLocal<>();

  public static <T> T get(final Class<T> target) {
    return (T) getContext(INNER.get(), target).getContent();
  }

  public static void reset() {
    INNER.remove();
  }

  private static Context findContext(final Context inner, final Class target) {
    if (inner != null //
        && target != null //
        && target.isAssignableFrom(inner.getClass())) {
      return inner;
    }
    if (inner != null && inner.parent != null) {
      return getContext(inner.parent, target);
    }
    return null;
  }

  private static Context getContext(final Context inner, final Class target) {
    final Context context = findContext(inner, target);
    if (context == null) {
      throw new IllegalArgumentExceptionBuilder() //
          .method("getContext") //
          .message("enclosing context type not found") //
          .field("thread", Thread.currentThread().getName()) //
          .field("target", target.getSimpleName()) //
          .build();
    }
    return context;
  }

  /** The parent context, or <code>null</code>. */
  private Context parent;

  protected Context() {
  }

  /** Enter context. Must be called from sub-classes when overridden. */
  public void enter() {
    if (findContext(INNER.get(), getClass()) != null) {
      throw new ErrorBuilder() //
          .method("enter") //
          .message("can not enter again") //
          .field("thread", Thread.currentThread().getName()) //
          .field("class", getClass().getSimpleName()) //
          .build();
    }
    final Context oldContext = INNER.get();
    this.parent = oldContext;
    INNER.set(this);
  }

  /** Exit context. Must be called from sub-classes when overridden. */
  public void exit() {
    final Context inner = INNER.get();
    if (inner == null) {
      throw new IllegalStateExceptionBuilder() //
          .method("exit") //
          .message("no current context") //
          .field("thread", Thread.currentThread().getName()) //
          .field("requested", getClass().getSimpleName()) //
          .build();
    }
    if (!inner.getClass().equals(getClass())) {
      throw new IllegalStateExceptionBuilder() //
          .method("exit") //
          .message("can only exit from current context") //
          .field("thread", Thread.currentThread().getName()) //
          .field("requested", getClass().getSimpleName()) //
          .field("current", inner.getClass().getSimpleName()) //
          .build();
    }
    final Context outerContext = inner.parent;
    if (outerContext == null) {
      INNER.remove();
    } else {
      INNER.set(outerContext);
    }
  }

  /**
   * Get content of this context. By default the context itself; which makes
   * everything that is public accessible. Or override to make only selected
   * content accessible.
   */
  protected T getContent() {
    return (T) this;
  }
}
