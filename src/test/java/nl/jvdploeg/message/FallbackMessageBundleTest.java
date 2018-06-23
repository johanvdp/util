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
    final Message message = new MessageBuilder(new MessageDefinition("litteral.[[x]].{{y}}", "propertyColor", "doorNumber")) //
        .add("propertyColor", "color.green") //
        .add("doorNumber", "2") //
        .build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("litteral.[x].{y}: doorNumber=2, propertyColor=color.green", translation);
  }

  @Test
  public void testWithArgumentIndirection() {
    final Message message = new MessageBuilder(new MessageDefinition("the.color.door", "propertyColor")) //
        .add("propertyColor", "color.green") //
        .build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("the.color.door: propertyColor=color.green", translation);
  }

  @Test
  public void testWithArgumentValue() {
    final Message message = new MessageBuilder(new MessageDefinition("open.door.number", "doorNumber")) //
        .add("doorNumber", "2") //
        .build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("open.door.number: doorNumber=2", translation);
  }

  @Test
  public void testWithoutArguments() {
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("color.green", translation);
  }

  @Test
  public void testTextWithFormat() {
    final Message message = new MessageBuilder(new MessageDefinition("product.weight", "weight")) //
        .add("weight", 2.3456d) //
        .build();
    final String translation = bundle.translate(message);
    Assert.assertEquals("product.weight: weight=2.3456", translation);
  }
}
