// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import nl.jvdploeg.context.Context;

public class MessageBundleContext extends Context<MessageBundle> implements MessageBundle {

  private final MessageBundle messageBundle;

  public MessageBundleContext(final MessageBundle messageBundle) {
    this.messageBundle = messageBundle;
  }

  @Override
  public final String translate(final Message message) {
    return messageBundle.translate(message);
  }

  @Override
  protected final MessageBundle getContent() {
    return messageBundle;
  }

  public static final String aware(final Message message) {
    MessageBundle messageBundle = null;
    try {
      messageBundle = Context.get(MessageBundleContext.class);
    } catch (final Exception e) {
      // no context provided
      messageBundle = new FallbackMessageBundle();
    }
    final String translated = messageBundle.translate(message);
    return translated;
  }
}
