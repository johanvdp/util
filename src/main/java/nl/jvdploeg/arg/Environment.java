// The author disclaims copyright to this source code.
package nl.jvdploeg.arg;

import java.util.Properties;

public final class Environment {

  private final boolean useEnv;
  private final boolean useProperties;

  public Environment() {
    this(true, true);
  }

  /**
   * Fully qualified constructor.<br>
   * When both environment variable and application property are in use and
   * available, the application property is used.
   *
   * @param useEnv
   *          true to evaluate environment variables
   * @param useProperties
   *          true to evaluate application properties
   */
  public Environment(final boolean useEnv, final boolean useProperties) {
    this.useEnv = useEnv;
    this.useProperties = useProperties;
  }

  public Integer getInteger(final String name, final Integer defaultValue) {
    final String stringDefaultValue = defaultValue == null ? null : defaultValue.toString();
    final String stringValue = getString(name, stringDefaultValue);
    final Integer integerValue = stringValue == null ? null : Integer.valueOf(stringValue);
    return integerValue;
  }

  public String getString(final String name, final String defaultValue) {

    if (useProperties) {
      final Properties properties = System.getProperties();
      if (properties.containsKey(name)) {
        final String propertyValue = properties.getProperty(name);
        return propertyValue;
      }
    }

    if (useEnv) {
      final String envValue = System.getenv(name);
      if (envValue != null) {
        return envValue;
      }
    }

    return defaultValue;
  }
}
