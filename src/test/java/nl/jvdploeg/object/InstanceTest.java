// The author disclaims copyright to this source code.
package nl.jvdploeg.object;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class InstanceTest {

  @Test
  public void testIdentity() {
    // given
    final ArrayList<Integer> object = new ArrayList<>();
    // when
    final String identity = Instance.identity(object);
    // then
    Assert.assertEquals(object.getClass().getSimpleName() + "@" + System.identityHashCode(object), identity);
  }

  @Test
  public void testIdentity_Null() {
    // given
    final ArrayList<Integer> object = null;
    // when
    final String identity = Instance.identity(object);
    // then
    Assert.assertEquals("null", identity);
  }
}
