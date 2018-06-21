// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Locale;

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

  @Override
  protected Locale getLocale() {
    return Locale.getDefault();
  }
}
