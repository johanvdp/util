// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

public final class ArgumentCheckTest extends AbstractLimitCheckTest {

  @Override
  protected LimitCheck createCheck() {
    return Checks.ARGUMENT;
  }

  @Override
  protected String getCheckSpecificMessage() {
    return "Illegal argument.";
  }
}
