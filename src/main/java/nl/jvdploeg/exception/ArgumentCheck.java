// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;

public class ArgumentCheck extends LimitCheck {

  public ArgumentCheck() {
    super();
  }

  @Override
  protected final void consequence(final Message message) {
    final Message illegalArgumentMessage = new MessageBuilder("Illegal argument.").build();
    throw new MessageException(illegalArgumentMessage, message);
  }
}
