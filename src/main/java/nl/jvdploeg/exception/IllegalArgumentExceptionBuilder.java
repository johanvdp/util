package nl.jvdploeg.exception;

public final class IllegalArgumentExceptionBuilder extends ThrowableBuilder<IllegalArgumentException> {

  public IllegalArgumentExceptionBuilder() {
  }

  @Override
  protected IllegalArgumentException create(final String message, final Throwable cause) {
    return new IllegalArgumentException(message, cause);
  }
}
