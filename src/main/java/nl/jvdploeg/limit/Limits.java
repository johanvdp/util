// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

public final class Limits {

  private Limits() {
  }

  /**
   * String double &gt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final String DOUBLE_GE = "%1$s (%2$f) should be greater than or equal to %3$f";
  /**
   * String double &gt; limit message format.
   *
   * @see #check(Object)
   */
  private static final String DOUBLE_GT = "%1$s (%2$f) should be greater than %3$f";
  /**
   * String double &lt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final String DOUBLE_LE = "%1$s (%2$f) should be less than or equal to %3$f";
  /**
   * String double &lt; limit message format.
   *
   * @see #check(Object)
   */
  private static final String DOUBLE_LT = "%1$s (%2$f) should be less than %3$f";
  /**
   * String number &gt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final String INTEGER_GE = "%1$s (%2$d) should be greater than or equal to %3$d";
  /**
   * String number &gt; limit message format.
   *
   * @see #check(Object)
   */
  private static final String INTEGER_GT = "%1$s (%2$d) should be greater than %3$d";
  /**
   * String number &lt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final String INTEGER_LE = "%1$s (%2$d) should be less than or equal to %3$d";
  /**
   * String number &lt; limit message format.
   *
   * @see #check(Object)
   */
  private static final String INTEGER_LT = "%1$s (%2$d) should be less than %3$d";
  /**
   * String maximum length limit message format.
   *
   * @see #check(Object)
   */
  private static final String STRING_MAXLENGTH = "%1$s (%2$s) length should be less than or equal to %3$d";
  /**
   * String minimum length limit message format.
   *
   * @see #check(Object)
   */
  private static final String STRING_MINLENGTH = "%1$s (%2$s) length should be greater than or equal to %3$d";
  /**
   * String pattern limit message format.
   *
   * @see #check(Object)
   */
  private static final String STRING_PATTERN = "%1$s (%2$s) pattern should match %3$s";

  public static Limit<Double, Double> ge(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() >= limit.doubleValue()), DOUBLE_GE);
  }

  public static Limit<Integer, Integer> ge(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() >= limit.intValue()), INTEGER_GE);
  }

  public static Limit<Long, Long> ge(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() >= limit.longValue()), INTEGER_GE);
  }

  public static Limit<Double, Double> gt(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() > limit.doubleValue()), DOUBLE_GT);
  }

  public static Limit<Integer, Integer> gt(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() > limit.intValue()), INTEGER_GT);
  }

  public static Limit<Long, Long> gt(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() > limit.longValue()), INTEGER_GT);
  }

  public static Limit<Double, Double> le(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() <= limit.doubleValue()), DOUBLE_LE);
  }

  public static Limit<Integer, Integer> le(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() <= limit.intValue()), INTEGER_LE);
  }

  public static Limit<Long, Long> le(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() <= limit.longValue()), INTEGER_LE);
  }

  public static Limit<Double, Double> lt(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() < limit.doubleValue()), DOUBLE_LT);
  }

  public static Limit<Integer, Integer> lt(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() < limit.intValue()), INTEGER_LT);
  }

  public static Limit<Long, Long> lt(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() < limit.longValue()), INTEGER_LT);
  }

  public static Limit<String, String> matches(final String name, final String pattern) {
    return new Limit<>(name, pattern, f -> f.matches(pattern), STRING_PATTERN);
  }

  public static Limit<String, Integer> maxLength(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.length() <= limit.intValue()), STRING_MAXLENGTH);
  }

  public static Limit<String, Integer> minLength(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.length() >= limit.intValue()), STRING_MINLENGTH);
  }
}
