package nl.jvdploeg.exception;

import nl.jvdploeg.object.Instance;

/**
 * Build exception combining method, messages, values, and cause when available.
 */
public abstract class ThrowableBuilder<T extends Throwable> {

  private final StringBuilder messages = new StringBuilder();
  private final StringBuilder values = new StringBuilder();
  private Throwable oneCause;
  private String oneMethod;

  protected ThrowableBuilder() {
  }

  /**
   * Build exception combining method, messages, values, and cause when
   * available.
   */
  public final T build() {

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
    final T throwable = create(message.toString(), oneCause);
    return throwable;
  }

  protected abstract T create(String message, Throwable cause);

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
