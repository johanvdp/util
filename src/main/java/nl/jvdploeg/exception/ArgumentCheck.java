// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageDefinition;

public class ArgumentCheck extends LimitCheck {

  private static final MessageDefinition SENTENCE_ILLEGAL_ARGUMENT = new MessageDefinition("illegal.argument");
  private static final Message MESSAGE_ILLEGAL_ARGUMENT = new MessageBuilder(SENTENCE_ILLEGAL_ARGUMENT).build();

  public ArgumentCheck() {
    super();
  }

  @Override
  protected final void consequence(final Message message) {
    throw new MessageException(MESSAGE_ILLEGAL_ARGUMENT, message);
  }
}
