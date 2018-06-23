// The author disclaims copyright to this source code.
package nl.jvdploeg.limit;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.jvdploeg.message.FallbackMessageBundle;
import nl.jvdploeg.message.Message;
import nl.jvdploeg.message.MessageDefinition;

public class LimitTest {

  private final FallbackMessageBundle bundle = new FallbackMessageBundle();
  private Limit<String, Integer> limit;

  @Before
  public void before() {
    final Predicate<String> predicate = t -> t.length() > 3;
    final MessageDefinition definition = new MessageDefinition("limit.check.minlength", "name", "value", "limit");
    limit = new Limit<>("x", Integer.valueOf(3), predicate, definition);
  }

  @Test
  public void testPredicateFalse() {
    Assert.assertEquals("limit.check.minlength: name=x, limit=3, value=aaa", toString(limit.check("aaa")));
  }

  @Test
  public void testPredicateTrue() {
    Assert.assertEquals(null, limit.check("aaaa"));
  }

  private String toString(final Message message) {
    return bundle.translate(message);
  }
}
