// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import org.junit.Assert;
import org.junit.Test;

public class CauseTest {

  @Test
  public void testGetCause_MessageNull() {
    // given
    final Throwable t1 = new Throwable("message1");
    final Throwable t2 = new Throwable(null, t1);
    final Throwable t3 = new Throwable("message3", t2);
    // when
    final String cause = Cause.getCause(t3);
    // then
    Assert.assertEquals("message3; message1", cause);
  }

  @Test
  public void testGetCause_Recurse() {
    // given
    final Throwable t1 = new Throwable("message1");
    final Throwable t2 = new Throwable("message2", t1);
    t1.initCause(t2);

    // when
    final String cause = Cause.getCause(t1);
    // then
    Assert.assertEquals("message1; message2", cause);
  }

  @Test
  public void testGetCause_ThrowableNull() {
    // given
    final Throwable t1 = null;
    // when
    final String cause = Cause.getCause(t1);
    // then
    Assert.assertEquals("", cause);
  }
}
