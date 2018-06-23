// The author disclaims copyright to this source code.
package nl.jvdploeg.context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContextTest {

  @Before
  public void before() {
    Context.reset();
  }

  @Test
  public void testEnter() {
    // given
    final TestContext context = new TestContext();
    // when
    context.enter();
    // then
    Assert.assertSame(context.getContent(), Context.get(TestContext.class));
  }

  @Test(expected = IllegalStateException.class)
  public void testExit() {
    // given
    final TestContext context = new TestContext();
    context.enter();
    // when
    context.exit();
    // then
    Context.get(TestContext.class);
    Assert.fail("exception expected");
  }

  @Test
  public void testNesting() {
    // given
    final TestContext context1 = new TestContext();
    final CountingContext context2 = new CountingContext();
    // when
    context1.enter();
    context2.enter();
    // then
    Assert.assertSame(context1.getContent(), Context.get(TestContext.class));
    Assert.assertSame(context2.getContent(), Context.get(CountingContext.class));
    // when
    context2.exit();
    context1.exit();
    // then
    try {
      Context.get(TestContext.class);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      Context.get(CountingContext.class);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testNesting_Order() {
    // given
    final TestContext context1 = new TestContext();
    final CountingContext context2 = new CountingContext();
    context1.enter();
    context2.enter();
    // when/then
    context1.exit();
    // then
    Assert.fail("exception expected");
  }

  @Test(expected = IllegalStateException.class)
  public void testNone() {
    // given
    // when
    Context.get(TestContext.class);
    // then
    Assert.fail("exception expected");
  }
}
