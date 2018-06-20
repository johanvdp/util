// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

/**
 * MessageBundle that performs no translation.<br>
 */
public final class FallbackMessageBundle extends AbstractMessageBundle {

  /**
   * Constructor.
   */
  public FallbackMessageBundle() {
  }

  @Override
  protected String getString(final String key) {
    return key;
  }
}
