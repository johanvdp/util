package nl.jvdploeg.arg;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnvironmentTest {

  private static final Integer DEFAULT_INTEGER_VALUE = Integer.valueOf(12345);
  private static final String DEFAULT_STRING_VALUE = "DEFAULT_VALUE";
  private static final String NON_EXISTING_ENV_VARIABLE_NAME = "__NON_EXISTING_ENV_VARIABLE_NAME__";
  private static final String NON_EXISTING_PROPERTY_NAME = "__NON_EXISTING_PROPERTY_NAME__";
  private static String existingEnvName;
  private static String existingPropertyName;
  private static String commonName;

  @BeforeClass
  public static void beforeClass() {
    existingEnvName = System.getenv().keySet().toArray(new String[0])[0];
    commonName = System.getenv().keySet().toArray(new String[0])[1];
    // the environment variables are an unmodifyable set
    // create environment variable name in application properties to have a
    // common name
    System.getProperties().setProperty(commonName, "propertyValue");
    existingPropertyName = System.getProperties().keySet().toArray(new String[0])[0];
  }

  @Test
  public void defaultConstructor_GetString_ReturnBoth() {

    final Environment environment = new Environment();
    final String envActual = environment.getString(existingEnvName, null);
    Assert.assertNotNull(envActual);

    final String propertyActual = environment.getString(existingPropertyName, null);
    Assert.assertNotNull(propertyActual);
  }

  @Test
  public void useGetInteger_None_NullDefault_ReturnNull() {

    final Environment environment = new Environment(false, false);
    final Integer actual = environment.getInteger(NON_EXISTING_PROPERTY_NAME, null);
    Assert.assertEquals(null, actual);
  }

  @Test
  public void useGetInteger_None_WithDefault_ReturnDefault() {

    final Environment environment = new Environment(false, false);
    final Integer actual = environment.getInteger(NON_EXISTING_PROPERTY_NAME, DEFAULT_INTEGER_VALUE);
    Assert.assertEquals(DEFAULT_INTEGER_VALUE, actual);
  }

  @Test
  public void useGetString_BothExisting_GetProperty() {

    final Environment environment = new Environment(true, true);
    final String actual = environment.getString(commonName, null);
    Assert.assertEquals("propertyValue", actual);
  }

  @Test
  public void useGetString_Env_EnvOnly() {

    final Environment environment = new Environment(true, false);
    final String envActual = environment.getString(existingEnvName, null);
    Assert.assertNotNull(envActual);

    final String propertyActual = environment.getString(existingPropertyName, null);
    Assert.assertNull(propertyActual);
  }

  @Test
  public void useGetString_EnvironmentExisting() {

    final Environment environment = new Environment(true, false);
    final String expected = System.getenv(existingEnvName);
    final String actual = environment.getString(existingEnvName, null);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void useGetString_EnvironmentNotExisting_ReturnDefault() {

    final Environment environment = new Environment(true, false);
    final String actual = environment.getString(NON_EXISTING_ENV_VARIABLE_NAME, DEFAULT_STRING_VALUE);
    Assert.assertEquals(DEFAULT_STRING_VALUE, actual);
  }

  @Test
  public void useGetString_None_Existing_returnDefault() {

    final Environment environment = new Environment(false, false);
    final String envActual = environment.getString(existingEnvName, DEFAULT_STRING_VALUE);
    Assert.assertEquals(DEFAULT_STRING_VALUE, envActual);

    final String propertyActual = environment.getString(existingPropertyName, DEFAULT_STRING_VALUE);
    Assert.assertEquals(DEFAULT_STRING_VALUE, propertyActual);
  }

  @Test
  public void useGetString_None_NotExisting_ReturnDefault() {

    final Environment environment = new Environment(false, false);
    final String actual = environment.getString(NON_EXISTING_PROPERTY_NAME, DEFAULT_STRING_VALUE);
    Assert.assertEquals(DEFAULT_STRING_VALUE, actual);
  }

  @Test
  public void useGetString_Properties_PropertyOnly() {

    final Environment environment = new Environment(false, true);
    final String envActual = environment.getString(existingEnvName, null);
    Assert.assertNull(envActual);

    final String propertyActual = environment.getString(existingPropertyName, null);
    Assert.assertNotNull(propertyActual);
  }

  @Test
  public void useGetString_PropertyExisting() {

    final Environment environment = new Environment(false, true);
    final String expected = System.getProperty(existingPropertyName);
    final String actual = environment.getString(existingPropertyName, null);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void useGetString_PropertyNotExisting_ReturnDefault() {

    final Environment environment = new Environment(false, true);
    final String actual = environment.getString(NON_EXISTING_PROPERTY_NAME, DEFAULT_STRING_VALUE);
    Assert.assertEquals(DEFAULT_STRING_VALUE, actual);
  }
}
