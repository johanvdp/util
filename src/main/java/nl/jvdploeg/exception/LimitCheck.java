// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.limit.Limits;
import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.object.NullSafe;

public abstract class LimitCheck {

  private static final String OBJECT_NULL = "{name} should not be null";
  private static final String OBJECT_UNEXPECTED = "{name} {value} is unexpected";

  protected LimitCheck() {
  }

  public final void ge(final Double value, final String name, final Double limit) {
    check(Limits.ge(name, limit).check(value));
  }

  public final void ge(final Integer value, final String name, final Integer limit) {
    check(Limits.ge(name, limit).check(value));
  }

  public final void ge(final Long value, final String name, final Long limit) {
    check(Limits.ge(name, limit).check(value));
  }

  public final void gt(final Double value, final String name, final Double limit) {
    check(Limits.gt(name, limit).check(value));
  }

  public final void gt(final Integer value, final String name, final Integer limit) {
    check(Limits.gt(name, limit).check(value));
  }

  public final void gt(final Long value, final String name, final Long limit) {
    check(Limits.gt(name, limit).check(value));
  }

  public final void le(final Double value, final String name, final Double limit) {
    check(Limits.le(name, limit).check(value));
  }

  public final void le(final Integer value, final String name, final Integer limit) {
    check(Limits.le(name, limit).check(value));
  }

  public final void le(final Long value, final String name, final Long limit) {
    check(Limits.le(name, limit).check(value));
  }

  public final void lt(final Double value, final String name, final Double limit) {
    check(Limits.lt(name, limit).check(value));
  }

  public final void lt(final Integer value, final String name, final Integer limit) {
    check(Limits.lt(name, limit).check(value));
  }

  public final void lt(final Long value, final String name, final Long limit) {
    check(Limits.lt(name, limit).check(value));
  }

  public final void matches(final String value, final String name, final String pattern) {
    check(Limits.matches(name, pattern).check(value));
  }

  public final void maxLength(final String value, final String name, final Integer limit) {
    check(Limits.maxLength(name, limit).check(value));
  }

  public final void minLength(final String value, final String name, final Integer limit) {
    check(Limits.minLength(name, limit).check(value));
  }

  public final void notNull(final Object value, final String name) {
    if (value == null) {
      final Message message = new MessageBuilder(OBJECT_NULL) //
          .add("name", name) //
          .build();
      consequence(message);
    }
  }

  public final void unexpected(final Object value, final String name) {
    final Message message = new MessageBuilder(OBJECT_UNEXPECTED) //
        .add("name", name) //
        .add("value", NullSafe.toString(value)) //
        .build();
    consequence(message);
  }

  protected final void check(final Message message) {
    if (message != null) {
      consequence(message);
    }
  }

  protected abstract void consequence(Message message);
}
