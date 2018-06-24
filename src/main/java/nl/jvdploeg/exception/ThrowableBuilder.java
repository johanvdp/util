// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import java.util.function.BiFunction;

import nl.jvdploeg.object.Instance;

/**
 * Build exception combining method, messages, values, and cause when available.
 */
public class ThrowableBuilder<T extends Throwable> {

  private static final BiFunction<String, Throwable, RuntimeException> RUNTIME_EXCEPTION_FACTORY = //
      (msg, cause) -> new RuntimeException(msg, cause);
  private static final BiFunction<String, Throwable, IllegalArgumentException> ILLEGAL_ARGUMENT_EXCEPTION_FACTORY = //
      (msg, cause) -> new IllegalArgumentException(msg, cause);
  private static final BiFunction<String, Throwable, IllegalStateException> ILLEGAL_STATE_EXCEPTION_FACTORY = //
      (msg, cause) -> new IllegalStateException(msg, cause);

  public static ThrowableBuilder<IllegalArgumentException> createIllegalArgumentExceptionBuilder() {
    return new ThrowableBuilder<>(ILLEGAL_ARGUMENT_EXCEPTION_FACTORY);
  }

  public static ThrowableBuilder<IllegalStateException> createIllegalStateExceptionBuilder() {
    return new ThrowableBuilder<>(ILLEGAL_STATE_EXCEPTION_FACTORY);
  }

  public static ThrowableBuilder<RuntimeException> createRuntimeExceptionBuilder() {
    return new ThrowableBuilder<>(RUNTIME_EXCEPTION_FACTORY);
  }

  private final BiFunction<String, Throwable, T> exceptionFactory;
  private final StringBuilder messages = new StringBuilder();
  private final StringBuilder values = new StringBuilder();
  private Throwable oneCause;
  private String oneMethod;

  public ThrowableBuilder(final BiFunction<String, Throwable, T> exceptionFactory) {
    this.exceptionFactory = exceptionFactory;
  }

  /**
   * Build exception combining method, messages, values, and cause when
   * available.
   */
  public T build() {

    final StringBuilder message = new StringBuilder();
    if (oneMethod != null) {
      message.append(oneMethod);
    }
    if (messages.length() > 0) {
      if (message.length() > 0) {
        message.append(";");
      }
      message.append(messages.toString());
    }
    if (values.length() > 0) {
      if (message.length() > 0) {
        message.append(";");
      }
      message.append(values.toString());
    }
    final T throwable = exceptionFactory.apply(message.toString(), oneCause);
    return throwable;
  }

  public final ThrowableBuilder<T> cause(final Throwable cause) {
    oneCause = cause;
    return this;
  }

  public final ThrowableBuilder<T> field(final String name, final String value) {
    if (values.length() > 0) {
      values.append(";");
    }
    values.append(name);
    values.append("=");
    values.append(value);
    return this;
  }

  public final ThrowableBuilder<T> identity(final String name, final Object object) {
    if (values.length() > 0) {
      values.append(";");
    }
    values.append(name);
    values.append("=");
    values.append(Instance.identity(object));
    return this;
  }

  public final ThrowableBuilder<T> message(final String message) {
    if (messages.length() > 0) {
      messages.append(";");
    }
    messages.append(message);
    return this;
  }

  public final ThrowableBuilder<T> method(final String method) {
    oneMethod = method;
    return this;
  }
}
