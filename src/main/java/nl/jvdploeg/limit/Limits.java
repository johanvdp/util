// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import nl.jvdploeg.message.MessageDefinition;

public final class Limits {

  /**
   * Argument names common to all limit messages.Fs
   */
  private static final String[] LIMIT_ARGUMENT_NAMES = new String[] { "name", "value", "limit" };

  /**
   * Value &gt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_GE = new MessageDefinition("should.be.greater.than.or.equal.to", LIMIT_ARGUMENT_NAMES);

  /**
   * Value &gt; limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_GT = new MessageDefinition("should.be.greater.than", LIMIT_ARGUMENT_NAMES);
  /**
   * Value &lt;= limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_LE = new MessageDefinition("should.be.less.than.or.equal.to", LIMIT_ARGUMENT_NAMES);
  /**
   * Value &lt; limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_LT = new MessageDefinition("should.be.less.than", LIMIT_ARGUMENT_NAMES);
  /**
   * Value maximum length limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_MAXLENGTH = new MessageDefinition("length.should.be.less.than.or.equal.to", LIMIT_ARGUMENT_NAMES);
  /**
   * Value minimum length limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_MINLENGTH = new MessageDefinition("length.should.be.greater.than.or.equal.to",
      LIMIT_ARGUMENT_NAMES);
  /**
   * Value pattern limit message format.
   *
   * @see #check(Object)
   */
  private static final MessageDefinition SENTENCE_PATTERN = new MessageDefinition("pattern.should.match", LIMIT_ARGUMENT_NAMES);

  public static Limit<Double, Double> ge(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() >= limit.doubleValue()), SENTENCE_GE);
  }

  public static Limit<Integer, Integer> ge(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() >= limit.intValue()), SENTENCE_GE);
  }

  public static Limit<Long, Long> ge(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() >= limit.longValue()), SENTENCE_GE);
  }

  public static Limit<Double, Double> gt(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() > limit.doubleValue()), SENTENCE_GT);
  }

  public static Limit<Integer, Integer> gt(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() > limit.intValue()), SENTENCE_GT);
  }

  public static Limit<Long, Long> gt(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() > limit.longValue()), SENTENCE_GT);
  }

  public static Limit<Double, Double> le(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() <= limit.doubleValue()), SENTENCE_LE);
  }

  public static Limit<Integer, Integer> le(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() <= limit.intValue()), SENTENCE_LE);
  }

  public static Limit<Long, Long> le(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() <= limit.longValue()), SENTENCE_LE);
  }

  public static Limit<Double, Double> lt(final String name, final Double limit) {
    return new Limit<>(name, limit, f -> (f.doubleValue() < limit.doubleValue()), SENTENCE_LT);
  }

  public static Limit<Integer, Integer> lt(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.intValue() < limit.intValue()), SENTENCE_LT);
  }

  public static Limit<Long, Long> lt(final String name, final Long limit) {
    return new Limit<>(name, limit, f -> (f.longValue() < limit.longValue()), SENTENCE_LT);
  }

  public static Limit<String, String> matches(final String name, final String pattern) {
    return new Limit<>(name, pattern, f -> f.matches(pattern), SENTENCE_PATTERN);
  }

  public static Limit<String, Integer> maxLength(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.length() <= limit.intValue()), SENTENCE_MAXLENGTH);
  }

  public static Limit<String, Integer> minLength(final String name, final Integer limit) {
    return new Limit<>(name, limit, f -> (f.length() >= limit.intValue()), SENTENCE_MINLENGTH);
  }

  private Limits() {
  }
}
