// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageDefinition;

public abstract class Checks {

  private static final MessageDefinition SENTENCE_ILLEGAL_STATE = new MessageDefinition("illegal.state");
  private static final Message MESSAGE_ILLEGAL_STATE = new MessageBuilder(SENTENCE_ILLEGAL_STATE).build();

  private static final MessageDefinition SENTENCE_ILLEGAL_ARGUMENT = new MessageDefinition("illegal.argument");
  private static final Message MESSAGE_ILLEGAL_ARGUMENT = new MessageBuilder(SENTENCE_ILLEGAL_ARGUMENT).build();

  public static final LimitCheck ARGUMENT = new LimitCheck(message -> {
    throw new MessageException(MESSAGE_ILLEGAL_ARGUMENT, message);
  });

  public static final LimitCheck STATE = new LimitCheck(message -> {
    throw new MessageException(MESSAGE_ILLEGAL_STATE, message);
  });

  private Checks() {
  }
}
