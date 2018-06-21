// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageBuilder;
import nl.jvdploeg.message.MessageBundleContext;

public class LimitTest {

  private Limit<String, Integer> limit;

  @Before
  public void before() {
    final Predicate<String> predicate = t -> t.length() > 3;
    final Message message = new MessageBuilder("{name} {value} {limit}").build();
    limit = new Limit<>("name", Integer.valueOf(3), predicate, message);
  }

  @Test
  public void testPredicateFalse() {
    Assert.assertEquals("name aaa 3", toString(limit.check("aaa")));
  }

  @Test
  public void testPredicateTrue() {
    Assert.assertEquals(null, limit.check("aaaa"));
  }

  private String toString(final Message message) {
    return MessageBundleContext.aware(message);
  }
}
