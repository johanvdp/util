// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageDefinition;

public class StateCheck extends LimitCheck {

  private static final MessageDefinition SENTENCE_ILLEGAL_STATE = new MessageDefinition("illegal.state");
  private static final Message MESSAGE_ILLEGAL_STATE = new MessageBuilder(SENTENCE_ILLEGAL_STATE).build();

  public StateCheck() {
    super();
  }

  @Override
  protected final void consequence(final Message message) {
    throw new MessageException(MESSAGE_ILLEGAL_STATE, message);
  }
}
