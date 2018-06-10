// The author disclaims copyright to this source code.
package nl.jvdploeg.object;

import org.junit.Assert;
import org.junit.Test;

public class NullSafeTest {

  // not using the String object because of deduplication
  // not using small Integer objects because these use a factory
  private static final Integer VALUE = Integer.valueOf(Integer.MAX_VALUE);
  private static final Integer NULL = null;
  private static final Integer EQUAL = Integer.valueOf(Integer.MAX_VALUE);

  @Test
  public void testCompareTo() {
    Assert.assertTrue(NullSafe.compareTo(NULL, NULL) == 0);
    Assert.assertTrue(NullSafe.compareTo(NULL, VALUE) < 0);
    Assert.assertTrue(NullSafe.compareTo(VALUE, NULL) > 0);
    Assert.assertTrue(NullSafe.compareTo(VALUE, EQUAL) == 0);
  }

  @Test
  public void testEquals() {
    Assert.assertTrue(NullSafe.equals(NULL, NULL));
    Assert.assertFalse(NullSafe.equals(NULL, VALUE));
    Assert.assertFalse(NullSafe.equals(VALUE, NULL));
    Assert.assertTrue(NullSafe.equals(VALUE, EQUAL));
  }

  @Test
  public void testFunction() {
    Assert.assertEquals("input", NullSafe.function("input", f -> f));
    Assert.assertEquals(null, NullSafe.function(null, f -> f));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(0, NullSafe.hashCode(NULL));
    Assert.assertEquals(VALUE.hashCode(), NullSafe.hashCode(VALUE));
  }

  @Test
  public void testToString() {
    Assert.assertEquals("null", NullSafe.toString(NULL));
    Assert.assertEquals("other", NullSafe.toString("other"));
  }
}
