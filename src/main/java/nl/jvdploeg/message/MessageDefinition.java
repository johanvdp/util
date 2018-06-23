// The author disclaims copyright to this source code.
package nl.jvdploeg.message;

import java.util.Collections;
import java.util.Set;

import nl.jvdploeg.util.Arrays;

public final class MessageDefinition {

  private final String key;
  private final Set<String> argumentNames;

  public MessageDefinition(final String key, final String... argumentNames) {
    if (key == null) {
      throw new IllegalArgumentException("key is null");
    }
    if (argumentNames == null) {
      throw new IllegalArgumentException("argumentNames is null");
    }
    for (final String argumentName : argumentNames) {
      if (argumentName == null) {
        throw new IllegalArgumentException("argumentName is null");
      }
    }
    this.key = key;
    this.argumentNames = Collections.unmodifiableSet(Arrays.asSet(argumentNames));
  }

  public Set<String> getArgumentNames() {
    return argumentNames;
  }

  public String getKey() {
    return key;
  }
}
