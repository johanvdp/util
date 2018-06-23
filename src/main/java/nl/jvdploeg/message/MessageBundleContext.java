// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import nl.jvdploeg.context.Context;

public class MessageBundleContext extends Context<MessageBundle> implements MessageBundle {

  public static final String aware(final Message... messages) {
    final StringBuilder builder = new StringBuilder();
    for (final Message message : messages) {
      if (builder.length() > 0) {
        builder.append('\n');
      }
      final String one = aware(message);
      builder.append(one);
    }
    final String result = builder.toString();
    return result;
  }

  public static final String aware(final Message message) {
    MessageBundle bundle = null;
    try {
      bundle = Context.get(MessageBundleContext.class);
    } catch (final Exception e) {
      // no context provided
      bundle = new FallbackMessageBundle();
    }
    final String translated = bundle.translate(message);
    return translated;
  }

  private final List<MessageBundle> messageBundles = new ArrayList<>();

  public MessageBundleContext(final Class<?>... classes) {
    addMessageBundles(classes);
  }

  public MessageBundleContext(final MessageBundle... bundles) {
    addMessageBundles(bundles);
  }

  public final void addMessageBundle(final Class<?> clasz) {
    final String name = clasz.getName();
    final ResourceBundle bundle = ResourceBundle.getBundle(name);
    final ResourceMessageBundle messageBundle = new ResourceMessageBundle(bundle);
    messageBundles.add(messageBundle);
  }

  public final void addMessageBundle(final MessageBundle bundle) {
    messageBundles.add(bundle);
  }

  public final void addMessageBundles(final Class<?>... classes) {
    for (final Class<?> clasz : classes) {
      addMessageBundle(clasz);
    }
  }

  public final void addMessageBundles(final MessageBundle... bundles) {
    for (final MessageBundle bundle : bundles) {
      addMessageBundle(bundle);
    }
  }

  @Override
  public final String translate(final Message message) {
    RuntimeException error = null;
    for (final MessageBundle messageBundle : messageBundles) {
      try {
        final String translation = messageBundle.translate(message);
        return translation;
      } catch (final RuntimeException e) {
        error = e;
      }
    }
    throw error;
  }
}
