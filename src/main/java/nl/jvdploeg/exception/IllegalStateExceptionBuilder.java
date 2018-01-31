package nl.jvdploeg.exception;

public final class IllegalStateExceptionBuilder extends ThrowableBuilder<IllegalStateException> {

  public IllegalStateExceptionBuilder() {
  }

  @Override
  protected IllegalStateException create(final String message, final Throwable cause) {
    return new IllegalStateException(message, cause);
  }
}
