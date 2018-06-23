// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import java.util.function.Consumer;

import nl.jvdploeg.limit.Limits;
import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageDefinition;
import nl.jvdploeg.object.NullSafe;

public class LimitCheck {

  private static final MessageDefinition SENTENCE_OBJECT_NULL = new MessageDefinition("object.null", "name");
  private static final MessageDefinition SENTENCE_OBJECT_INVALID = new MessageDefinition("object.invalid", "name", "value");
  private static final MessageDefinition SENTENCE_OBJECT_MISSING = new MessageDefinition("object.missing", "name", "value");

  private final Consumer<Message> consequence;

  public LimitCheck(final Consumer<Message> consequence) {
    this.consequence = consequence;
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

  public final void invalid(final Object value, final String name) {
    final Message message = new MessageBuilder(SENTENCE_OBJECT_INVALID) //
        .add("name", name) //
        .add("value", NullSafe.toString(value)) //
        .build();
    consequence.accept(message);
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

  public final void missing(final Object value, final String name) {
    final Message message = new MessageBuilder(SENTENCE_OBJECT_MISSING) //
        .add("name", name) //
        .add("value", NullSafe.toString(value)) //
        .build();
    consequence.accept(message);
  }

  public final void notNull(final Object value, final String name) {
    if (value == null) {
      final Message message = new MessageBuilder(SENTENCE_OBJECT_NULL) //
          .add("name", name) //
          .build();
      consequence.accept(message);
    }
  }

  private void check(final Message message) {
    if (message != null) {
      consequence.accept(message);
    }
  }
}
