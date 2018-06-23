// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

public final class StateCheckTest extends AbstractLimitCheckTest {

  @Override
  protected LimitCheck createCheck() {
    return Checks.STATE;
  }

  @Override
  protected String getCheckSpecificMessage() {
    return "Illegal state.";
  }
}
