// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;

public final class Limits {

  private Limits() {
  }

  /**
   * String double &gt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final Message DOUBLE_GE = new MessageBuilder("{name} ({value}) should be greater than or equal to {limit}").build();
  /**
   * String double &gt; limit message format.
   *
   * @see #check(Object)
   */
  private static final Message DOUBLE_GT = new MessageBuilder("{name} ({value}) should be greater than {limit}").build();
  /**
   * String double &lt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final Message DOUBLE_LE = new MessageBuilder("{name} ({value}) should be less than or equal to {limit}").build();
  /**
   * String double &lt; limit message format.
   *
   * @see #check(Object)
   */
  private static final Message DOUBLE_LT = new MessageBuilder("{name} ({value}) should be less than {limit}").build();
  /**
   * String number &gt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final Message INTEGER_GE = new MessageBuilder("{name} ({value}) should be greater than or equal to {limit}").build();
  /**
   * String number &gt; limit message format.
   *
   * @see #check(Object)
   */
  private static final Message INTEGER_GT = new MessageBuilder("{name} ({value}) should be greater than {limit}").build();
  /**
   * String number &lt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final Message INTEGER_LE = new MessageBuilder("{name} ({value}) should be less than or equal to {limit}").build();
  /**
   * String number &lt; limit message format.
   *
   * @see #check(Object)
   */
  private static final Message INTEGER_LT = new MessageBuilder("{name} ({value}) should be less than {limit}").build();
  /**
   * String maximum length limit message format.
   *
   * @see #check(Object)
   */
  private static final Message STRING_MAXLENGTH = new MessageBuilder("{name} ({value}) length should be less than or equal to {limit}").build();
  /**
   * String minimum length limit message format.
   *
   * @see #check(Object)
   */
  private static final Message STRING_MINLENGTH = new MessageBuilder("{name} ({value}) length should be greater than or equal to {limit}").build();
  /**
   * String pattern limit message format.
   *
   * @see #check(Object)
   */
  private static final Message STRING_PATTERN = new MessageBuilder("{name} ({value}) pattern should match {limit}").build();

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
