// The author disclaims copyright to this source code.
package nl.jvdploeg.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class NamedThreadFactory implements ThreadFactory {

  private static String createName(final Class<?> owner, final String instance) {
    final StringBuilder builder = new StringBuilder();
    builder.append(owner.getSimpleName().toLowerCase());
    builder.append('-');
    if (instance != null) {
      builder.append(instance);
      builder.append('-');
    }
    return builder.toString();
  }

  /** Thread base name */
  private final String name;
  /** Thread daemon setting */
  private final boolean daemon;
  /** Instance number in thread name */
  private final AtomicLong instance = new AtomicLong(0);
  /** Thread priority, or <code>null</code> if not set */
  private final Integer priority;

  public NamedThreadFactory(final Class<?> owner) {
    this(owner, null, true, null);
  }

  public NamedThreadFactory(final Class<?> owner, final String instance, final boolean daemon, final Integer priority) {
    name = createName(owner, instance);
    this.daemon = daemon;
    this.priority = priority;
  }

  @Override
  public Thread newThread(final Runnable r) {
    final Thread thread = new Thread(r, next());
    thread.setDaemon(daemon);
    if (priority != null) {
      thread.setPriority(priority.intValue());
    }
    return thread;
  }

  private String next() {
    return name + instance.incrementAndGet();
  }
}
