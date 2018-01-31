package nl.jvdploeg.exception;

public class ArgumentCheck extends LimitCheck {

  public ArgumentCheck() {
    super();
  }

  @Override
  protected final void consequence(final String message) {
    throw new IllegalArgumentException(message);
  }
}
