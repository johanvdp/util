package nl.jvdploeg.message;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageBundleContextTest {

  @Before
  public void before() {
    Locale.setDefault(new Locale("nl"));
  }

  @Test
  public void testTranslate() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(TestBundle.class);
    context.enter();
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    // when / then
    Assert.assertEquals("groen", context.translate(message));
    // cleanup
    context.exit();
  }
}
