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
    final Message message = new MessageBuilder("Text with [propertyColor] and {doorNumber} and literally [[x]], {{y}}")
        .add("propertyColor", "color.green").add("doorNumber", "2").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("Text with color.green and 2 and literally [x], {y}", translation);
  }

  @Test
  public void testWithArgumentIndirection() {
    final Message message = new MessageBuilder("the.color.door").add("propertyColor", "color.green").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("the.color.door", translation);
  }

  @Test
  public void testWithArgumentValue() {
    final Message message = new MessageBuilder("open.door.number").add("doorNumber", "2").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("open.door.number", translation);
  }

  @Test
  public void testWithoutArguments() {
    final Message message = new MessageBuilder("color.green").build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("color.green", translation);
  }

  @Test
  public void testTextWithFormat() {
    final Message message = new MessageBuilder("product.weight {weight}") //
        .add("weight", 2.3456d) //
        .build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("product.weight 2.3456", translation);
  }
}
