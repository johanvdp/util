// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MessageBundle using ResourceBundle as backing.<br>
 */
public final class ResourceMessageBundle extends AbstractMessageBundle {

  private final ResourceBundle bundle;

  /**
   * Constructor.
   *
   * @param bundle
   *          Bundle with resources.
   */
  public ResourceMessageBundle(final ResourceBundle bundle) {
    this.bundle = bundle;
  }

  @Override
  protected String getString(final String key) {
    return bundle.getString(key);
  }

  @Override
  protected Locale getLocale() {
    return bundle.getLocale();
  }
}
