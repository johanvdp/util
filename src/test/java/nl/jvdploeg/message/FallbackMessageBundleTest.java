package nl.jvdploeg.message;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FallbackMessageBundleTest {

  private FallbackMessageBundle bundle;

  @Before
  public void before() {
    bundle = new FallbackMessageBundle();
  }

  @Test
  public void testTextWithLitterals() {
    final Message message = new MessageBuilder("text.with.litterals").add("propertyColor", "color.green").add("doorNumber", "2").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("Tekst met groen en 2 en letterlijk [x], {y}.", translation);
  }

  @Test
  public void testWithArgumentIndirection() {
    final Message message = new MessageBuilder("the.color.door").add("propertyColor", "color.green").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("De groen deur.", translation);
  }

  @Test
  public void testWithArgumentValue() {
    final Message message = new MessageBuilder("open.door.number").add("doorNumber", "2").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("Open deur nummer 2.", translation);
  }

  @Test
  public void testWithoutArguments() {
    final Message message = new MessageBuilder("color.green").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("groen", translation);
  }
}
