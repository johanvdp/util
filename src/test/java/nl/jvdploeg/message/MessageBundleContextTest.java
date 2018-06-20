package nl.jvdploeg.message;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageBundleContextTest {

  private ResourceBundle resourceBundle;
  private MessageBundle messageBundle;

  @Before
  public void before() {
    resourceBundle = ResourceBundle.getBundle("nl.jvdploeg.message.TestBundle", new Locale("nl"));
    messageBundle = new ResourceMessageBundle(resourceBundle);
  }

  @Test
  public void testInContext_Instance() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(messageBundle);
    context.enter();
    final Message message = new MessageBuilder("color.green").build();
    // when / then
    Assert.assertEquals("groen", context.translate(message));
    // cleanup
    context.exit();
  }

  @Test
  public void testNoContext_Instance() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(messageBundle);
    final Message message = new MessageBuilder("color.green").build();
    // when / then
    Assert.assertEquals("groen", context.translate(message));
  }

  @Test
  public void testInContext_Static() {
    // given
    // in context
    final MessageBundleContext context = new MessageBundleContext(messageBundle);
    context.enter();
    final Message message = new MessageBuilder("color.green").build();
    // when / then
    Assert.assertEquals("groen", MessageBundleContext.aware(message));
    // cleanup
    context.exit();
  }

  @Test
  public void testNoContext_Static() {
    // given
    final Message message = new MessageBuilder("color.green").build();
    // when / then
    Assert.assertEquals("color.green", MessageBundleContext.aware(message));
  }
}
