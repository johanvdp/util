// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.jvdploeg.limit.Limits;
import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBundleContext;

public abstract class AbstractLimitCheckTest {

  private static final Double DOUBLE_GREATER = Double.valueOf(3.0d);
  private static final Double DOUBLE = Double.valueOf(2.0d);
  private static final Double DOUBLE_LESS = Double.valueOf(1.0d);
  private static final Long LONG_GREATER = Long.valueOf(3);
  private static final Long LONG = Long.valueOf(2);
  private static final Long LONG_LESS = Long.valueOf(1);
  private static final Integer INTEGER_GREATER = Integer.valueOf(3);
  private static final Integer INTEGER = Integer.valueOf(2);
  private static final Integer INTEGER_LESS = Integer.valueOf(1);

  private LimitCheck check;
  private MessageBundleContext context;

  @After
  public void after() {
    context.exit();
  }

  @Before
  public void before() {
    context = new MessageBundleContext(Limits.class, Checks.class, LimitCheck.class);
    context.enter();
    check = createCheck();
  }

  protected abstract LimitCheck createCheck();

  @Test
  public void testGeDouble() {
    try {
      check.ge(DOUBLE_LESS, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1.0) should be greater than or equal to 2.0.", e);
    }
    try {
      check.ge(DOUBLE, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.ge(DOUBLE_GREATER, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGeInteger() {
    try {
      check.ge(INTEGER_LESS, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1) should be greater than or equal to 2.", e);
    }
    try {
      check.ge(INTEGER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.ge(INTEGER_GREATER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGeLong() {
    try {
      check.ge(LONG_LESS, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1) should be greater than or equal to 2.", e);
    }
    try {
      check.ge(LONG, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.ge(LONG_GREATER, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtDouble() {
    try {
      check.gt(DOUBLE_LESS, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1.0) should be greater than 2.0.", e);
    }
    try {
      check.gt(DOUBLE, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      check.gt(DOUBLE_GREATER, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtInteger() {
    try {
      check.gt(INTEGER_LESS, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1) should be greater than 2.", e);
    }
    try {
      check.gt(INTEGER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      check.gt(INTEGER_GREATER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtLong() {
    try {
      check.gt(LONG_LESS, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (1) should be greater than 2.", e);
    }
    try {
      check.gt(LONG, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      check.gt(LONG_GREATER, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testInvalid() {
    try {
      check.invalid(null, "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (null) is invalid.", e);
    }
    try {
      check.invalid("x", "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (x) is invalid.", e);
    }
  }

  protected final void assertException(final String expectedMessage, final Exception actualException) {
    Assert.assertEquals(getCheckSpecificMessage() + "\n" + expectedMessage, translate(actualException));
  }

  @Test
  public void testLeDouble() {
    try {
      check.le(DOUBLE_LESS, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(DOUBLE, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(DOUBLE_GREATER, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3.0) should be less than or equal to 2.0.", e);
    }
  }

  @Test
  public void testLeInteger() {
    try {
      check.le(INTEGER_LESS, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(INTEGER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(INTEGER_GREATER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3) should be less than or equal to 2.", e);
    }
  }

  @Test
  public void testLeLong() {
    try {
      check.le(LONG_LESS, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(LONG, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.le(LONG_GREATER, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3) should be less than or equal to 2.", e);
    }
  }

  @Test
  public void testLtDouble() {
    try {
      check.lt(DOUBLE_LESS, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.lt(DOUBLE, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (2.0) should be less than 2.0.", e);
    }
    try {
      check.lt(DOUBLE_GREATER, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3.0) should be less than 2.0.", e);
    }
  }

  @Test
  public void testLtInteger() {
    try {
      check.lt(INTEGER_LESS, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.lt(INTEGER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (2) should be less than 2.", e);
    }
    try {
      check.lt(INTEGER_GREATER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3) should be less than 2.", e);
    }
  }

  @Test
  public void testLtLong() {
    try {
      check.lt(LONG_LESS, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.lt(LONG, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (2) should be less than 2.", e);
    }
    try {
      check.lt(LONG_GREATER, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (3) should be less than 2.", e);
    }
  }

  @Test
  public void testMaxLength() {
    try {
      check.maxLength("a", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.maxLength("aa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.maxLength("aaa", "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (aaa) length should be less than or equal to 2.", e);
    }
  }

  @Test
  public void testMinLength() {
    try {
      check.minLength("a", "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (a) length should be greater than or equal to 2.", e);
    }
    try {
      check.minLength("aa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      check.minLength("aaa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testMissing() {
    try {
      check.missing(null, "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (null) is missing.", e);
    }
    try {
      check.missing("x", "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (x) is missing.", e);
    }
  }

  @Test
  public void testNotNull() {
    try {
      check.notNull(null, "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X is null.", e);
    }
    try {
      check.notNull("x", "X");
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testPattern() {
    try {
      check.matches("z", "X", "[abc]");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      assertException("X (z) pattern should match [abc].", e);
    }
    try {
      check.matches("b", "X", "[abc]");
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  protected abstract String getCheckSpecificMessage();

  private String translate(final Exception e) {
    if (e instanceof MessageException) {
      final Message[] messages = ((MessageException) e).getMessages();
      return String.join("\n", context.translate(messages));
    } else {
      return e.getMessage();
    }
  }
}
