// The author disclaims copyright to this source code.
package nl.jvdploeg.concurrent;

import org.junit.Assert;
import org.junit.Test;

public class NamedThreadFactoryTest {

  private static final Runnable A_RUNNABLE = () -> {
  };

  @Test
  public void defaultConstructor_newThread() {
    final NamedThreadFactory factory = new NamedThreadFactory(getClass());
    final Thread newThread = factory.newThread(A_RUNNABLE);

    Assert.assertEquals("namedthreadfactorytest-1", newThread.getName());
    Assert.assertEquals(Thread.NORM_PRIORITY, newThread.getPriority());
    Assert.assertTrue(newThread.isDaemon());
  }

  @Test
  public void fullyQualifiedConstructor_newThread_sequence() {
    final NamedThreadFactory factory = new NamedThreadFactory(getClass(), "instance", false, Integer.valueOf(Thread.MAX_PRIORITY));
    final Thread newThread = factory.newThread(A_RUNNABLE);

    Assert.assertEquals("namedthreadfactorytest-instance-1", newThread.getName());
    Assert.assertEquals(Thread.MAX_PRIORITY, newThread.getPriority());
    Assert.assertFalse(newThread.isDaemon());

    final Thread newThread2 = factory.newThread(A_RUNNABLE);

    Assert.assertEquals("namedthreadfactorytest-instance-2", newThread2.getName());
    Assert.assertEquals(Thread.MAX_PRIORITY, newThread2.getPriority());
    Assert.assertFalse(newThread2.isDaemon());
  }
}
