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
  public void testInContext_Instance() {
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

  @Test
  public void testNoContext_Instance() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(TestBundle.class);
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    // when / then
    Assert.assertEquals("groen", context.translate(message));
  }

  @Test
  public void testInContext_Static() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(TestBundle.class);
    context.enter();
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    // when / then
    Assert.assertEquals("groen", MessageBundleContext.aware(message));
    // cleanup
    context.exit();
  }

  @Test
  public void testNoContext_Static() {
    // given
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    // when / then
    Assert.assertEquals("color.green", MessageBundleContext.aware(message));
  }
}
