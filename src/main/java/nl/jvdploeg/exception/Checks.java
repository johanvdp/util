// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

public abstract class Checks {

  public static final ArgumentCheck ARGUMENT = new ArgumentCheck();
  public static final StateCheck STATE = new StateCheck();

  private Checks() {
  }
}
