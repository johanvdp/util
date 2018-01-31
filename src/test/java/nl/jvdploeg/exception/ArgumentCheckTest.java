package nl.jvdploeg.exception;

import org.junit.Assert;
import org.junit.Test;

public class ArgumentCheckTest {

  private static final Double DOUBLE_GREATER = Double.valueOf(3.0d);
  private static final Double DOUBLE = Double.valueOf(2.0d);
  private static final Double DOUBLE_LESS = Double.valueOf(1.0d);
  private static final Long LONG_GREATER = Long.valueOf(3);
  private static final Long LONG = Long.valueOf(2);
  private static final Long LONG_LESS = Long.valueOf(1);
  private static final Integer INTEGER_GREATER = Integer.valueOf(3);
  private static final Integer INTEGER = Integer.valueOf(2);
  private static final Integer INTEGER_LESS = Integer.valueOf(1);

  @Test
  public void testGeDouble() {
    try {
      Checks.ARGUMENT.ge(DOUBLE_LESS, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1.000000) should be greater than or equal to 2.000000", e.getMessage());
    }
    try {
      Checks.ARGUMENT.ge(DOUBLE, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.ge(DOUBLE_GREATER, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGeInteger() {
    try {
      Checks.ARGUMENT.ge(INTEGER_LESS, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1) should be greater than or equal to 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.ge(INTEGER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.ge(INTEGER_GREATER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGeLong() {
    try {
      Checks.ARGUMENT.ge(LONG_LESS, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1) should be greater than or equal to 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.ge(LONG, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.ge(LONG_GREATER, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtDouble() {
    try {
      Checks.ARGUMENT.gt(DOUBLE_LESS, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1.000000) should be greater than 2.000000", e.getMessage());
    }
    try {
      Checks.ARGUMENT.gt(DOUBLE, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      Checks.ARGUMENT.gt(DOUBLE_GREATER, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtInteger() {
    try {
      Checks.ARGUMENT.gt(INTEGER_LESS, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1) should be greater than 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.gt(INTEGER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      Checks.ARGUMENT.gt(INTEGER_GREATER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testGtLong() {
    try {
      Checks.ARGUMENT.gt(LONG_LESS, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (1) should be greater than 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.gt(LONG, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
    }
    try {
      Checks.ARGUMENT.gt(LONG_GREATER, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testLeDouble() {
    try {
      Checks.ARGUMENT.le(DOUBLE_LESS, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(DOUBLE, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(DOUBLE_GREATER, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3.000000) should be less than or equal to 2.000000", e.getMessage());
    }
  }

  @Test
  public void testLeInteger() {
    try {
      Checks.ARGUMENT.le(INTEGER_LESS, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(INTEGER, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(INTEGER_GREATER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3) should be less than or equal to 2", e.getMessage());
    }
  }

  @Test
  public void testLeLong() {
    try {
      Checks.ARGUMENT.le(LONG_LESS, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(LONG, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.le(LONG_GREATER, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3) should be less than or equal to 2", e.getMessage());
    }
  }

  @Test
  public void testLtDouble() {
    try {
      Checks.ARGUMENT.lt(DOUBLE_LESS, "X", DOUBLE);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.lt(DOUBLE, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (2.000000) should be less than 2.000000", e.getMessage());
    }
    try {
      Checks.ARGUMENT.lt(DOUBLE_GREATER, "X", DOUBLE);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3.000000) should be less than 2.000000", e.getMessage());
    }
  }

  @Test
  public void testLtInteger() {
    try {
      Checks.ARGUMENT.lt(INTEGER_LESS, "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.lt(INTEGER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (2) should be less than 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.lt(INTEGER_GREATER, "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3) should be less than 2", e.getMessage());
    }
  }

  @Test
  public void testLtLong() {
    try {
      Checks.ARGUMENT.lt(LONG_LESS, "X", LONG);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.lt(LONG, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (2) should be less than 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.lt(LONG_GREATER, "X", LONG);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (3) should be less than 2", e.getMessage());
    }
  }

  @Test
  public void testMaxLength() {
    try {
      Checks.ARGUMENT.maxLength("a", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.maxLength("aa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.maxLength("aaa", "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (aaa) length should be less than or equal to 2", e.getMessage());
    }
  }

  @Test
  public void testMinLength() {
    try {
      Checks.ARGUMENT.minLength("a", "X", INTEGER);
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (a) length should be greater than or equal to 2", e.getMessage());
    }
    try {
      Checks.ARGUMENT.minLength("aa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
    try {
      Checks.ARGUMENT.minLength("aaa", "X", INTEGER);
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testNotNull() {
    try {
      Checks.ARGUMENT.notNull(null, "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X should not be null", e.getMessage());
    }
    try {
      Checks.ARGUMENT.notNull("x", "X");
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testPattern() {
    try {
      Checks.ARGUMENT.matches("z", "X", "[abc]");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X (z) pattern should match [abc]", e.getMessage());
    }
    try {
      Checks.ARGUMENT.matches("b", "X", "[abc]");
    } catch (final Exception e) {
      Assert.fail("exception not expected");
    }
  }

  @Test
  public void testUnexpected() {
    try {
      Checks.ARGUMENT.unexpected(null, "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X null is unexpected", e.getMessage());
    }
    try {
      Checks.ARGUMENT.unexpected("x", "X");
      Assert.fail("exception expected");
    } catch (final Exception e) {
      Assert.assertEquals("X x is unexpected", e.getMessage());
    }
  }
}
