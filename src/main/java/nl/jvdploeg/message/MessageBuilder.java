// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.HashMap;
import java.util.Map;

public final class MessageBuilder {

  private final DefaultMessage message;

  private static final class DefaultMessage implements Message {

    private final String key;
    private final Map<String, String> arguments = new HashMap<>();

    DefaultMessage(final String key) {
      this.key = key;
    }

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public Map<String, String> getArguments() {
      return arguments;
    }
  }

  public MessageBuilder(final String messageKey) {
    message = new DefaultMessage(messageKey);
  }

  public MessageBuilder add(final String argumentKey, final String argumentValue) {
    message.arguments.put(argumentKey, argumentValue);
    return this;
  }

  public Message build() {
    return message;
  }
}
