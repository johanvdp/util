// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MessageBuilder {

  private static final class DefaultMessage implements Message {

    private final String key;
    private final Map<String, Object> arguments = new HashMap<>();

    private DefaultMessage(final String key) {
      this.key = key;
    }

    @Override
    public Map<String, Object> getArguments() {
      return arguments;
    }

    @Override
    public String getKey() {
      return key;
    }
  }

  private final MessageDefinition definition;
  private final DefaultMessage message;

  /**
   * Start building a message that should match the definition.
   */
  public MessageBuilder(final MessageDefinition definition) {
    if (definition == null) {
      throw new IllegalArgumentException("definition is null");
    }
    this.definition = definition;
    message = new DefaultMessage(definition.getKey());
  }

  public MessageBuilder add(final String argumentKey, final Object argumentValue) {
    if (argumentKey == null) {
      throw new IllegalArgumentException("argumentKey is null");
    }
    if (!definition.getArgumentNames().contains(argumentKey)) {
      throw new IllegalArgumentException(String.format("argument key not in definition: %s", argumentKey));
    }
    if (message.getArguments().containsKey(argumentKey)) {
      throw new IllegalArgumentException(String.format("argument key already defined: %s", argumentKey));
    }
    message.getArguments().put(argumentKey, argumentValue);
    return this;
  }

  /**
   * Build message.
   *
   * @throws IllegalStateException
   *           if message arguments are missing.
   */
  public Message build() {
    final Set<String> definitionArgs = definition.getArgumentNames();
    final Set<String> messageArgs = message.getArguments().keySet();
    for (final String definitionArg : definitionArgs) {
      if (!messageArgs.contains(definitionArg)) {
        throw new IllegalStateException(String.format("message argument (%s) missing", definitionArg));
      }
    }
    return message;
  }
}
