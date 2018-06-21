// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBundleContext;

public final class MessageException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final Message[] messages;

  public MessageException(final Message... messages) {
    super(MessageBundleContext.aware(messages));
    this.messages = messages;
  }

  public Message[] getMessages() {
    return messages;
  }

  public MessageException create(final MessageException base, final Message detail) {
    final int baseNumberOfMessages = base.messages.length;
    final Message[] newMessages = new Message[baseNumberOfMessages + 1];
    System.arraycopy(base.messages, 0, newMessages, 0, baseNumberOfMessages);
    newMessages[baseNumberOfMessages] = detail;
    return new MessageException(newMessages);
  }
}
