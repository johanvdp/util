// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;

public class StateCheck extends LimitCheck {

  public StateCheck() {
    super();
  }

  @Override
  protected final void consequence(final Message message) {
    final Message illegalStateMessage = new MessageBuilder("Illegal state.").build();
    throw new MessageException(illegalStateMessage, message);
  }
}
