// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import nl.jvdploeg.context.Context;

public final class MessageBundleContext extends Context<MessageBundle> {

  private final List<MessageBundle> messageBundles = new ArrayList<>();

  public MessageBundleContext(final Class<?>... classes) {
    addMessageBundles(classes);
  }

  public MessageBundleContext(final String... baseNames) {
    addMessageBundles(baseNames);
  }

  private void addMessageBundle(final Class<?> clasz) {
    final String baseName = clasz.getName();
    addMessageBundle(baseName);
  }

  private void addMessageBundle(final String baseName) {
    final ResourceBundle bundle = ResourceBundle.getBundle(baseName);
    final ResourceMessageBundle messageBundle = new ResourceMessageBundle(bundle);
    messageBundles.add(messageBundle);
  }

  public void addMessageBundles(final Class<?>... classes) {
    if (classes == null) {
      throw new IllegalArgumentException("classes null");
    }
    if (classes.length == 0) {
      throw new IllegalArgumentException("classes empty");
    }
    for (final Class<?> clasz : classes) {
      addMessageBundle(clasz);
    }
  }

  public void addMessageBundles(final String... baseNames) {
    if (baseNames == null) {
      throw new IllegalArgumentException("baseNames null");
    }
    if (baseNames.length == 0) {
      throw new IllegalArgumentException("baseNames empty");
    }
    for (final String baseName : baseNames) {
      addMessageBundle(baseName);
    }
  }

  public String translate(final Message message) {
    RuntimeException error = null;
    for (final MessageBundle messageBundle : messageBundles) {
      try {
        final String translation = messageBundle.translate(message);
        return translation;
      } catch (final RuntimeException e) {
        error = e;
      }
    }
    if (error == null) {
      throw new IllegalStateException("context has no message bundles");
    }
    throw error;
  }

  /**
   * Translate messages.
   */
  public String[] translate(final Message... messages) {
    final List<String> translated = new ArrayList<>();
    if (messages != null) {
      for (final Message message : messages) {
        final String one = translate(message);
        translated.add(one);
      }
    }
    return translated.toArray(new String[translated.size()]);
  }
}
