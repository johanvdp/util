// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

public final class ErrorBuilder extends ThrowableBuilder<RuntimeException> {

  public ErrorBuilder() {
  }

  @Override
  protected RuntimeException create(final String message, final Throwable cause) {
    return new RuntimeException(message, cause);
  }
}
