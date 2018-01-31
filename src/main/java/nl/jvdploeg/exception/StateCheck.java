package nl.jvdploeg.exception;

public class StateCheck extends LimitCheck {

  public StateCheck() {
    super();
  }

  @Override
  protected final void consequence(final String message) {
    throw new IllegalStateException(message);
  }
}
