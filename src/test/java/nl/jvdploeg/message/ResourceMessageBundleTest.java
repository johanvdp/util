package nl.jvdploeg.message;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResourceMessageBundleTest {

  private ResourceMessageBundle messageBundle;
  private ResourceBundle resourceBundle;

  @Before
  public void before() {
    resourceBundle = ResourceBundle.getBundle("nl.jvdploeg.message.TestBundle", new Locale("nl"));
    messageBundle = new ResourceMessageBundle(resourceBundle);
  }

  @Test
  public void testDirectBundleAccess() {
    Assert.assertEquals("groen", resourceBundle.getString("color.green"));
  }

  @Test
  public void testTextWithLitterals() {
    final Message message = new MessageBuilder(
        new MessageDefinition("property.[propertyColor].door.{doorNumber}.literal.[[x]].{{y}}", "propertyColor", "doorNumber")) //
            .add("propertyColor", "color.green") //
            .add("doorNumber", 2) //
            .build();
    final String translation = messageBundle.translate(message);
    Assert.assertEquals("Tekst met groen en 2 en letterlijk [x], {y}.", translation);
  }

  @Test
  public void testWithArgumentIndirection() {
    final Message message = new MessageBuilder(new MessageDefinition("the.color.door", "propertyColor")) //
        .add("propertyColor", "color.green") //
        .build();
    final String translation = messageBundle.translate(message);
    Assert.assertEquals("De groen deur.", translation);
  }

  @Test
  public void testWithArgumentValue() {
    final Message message = new MessageBuilder(new MessageDefinition("open.door.number", "doorNumber")) //
        .add("doorNumber", "2") //
        .build();
    final String translation = messageBundle.translate(message);
    Assert.assertEquals("Open deur nummer 2.", translation);
  }

  @Test
  public void testWithoutArguments() {
    final Message message = new MessageBuilder(new MessageDefinition("color.green")).build();
    final String translation = messageBundle.translate(message);
    Assert.assertEquals("groen", translation);
  }

  @Test
  public void testTextWithFormat() {
    final Message message = new MessageBuilder(new MessageDefinition("product.weight {weight}", "weight")) //
        .add("weight", 2.3456d) //
        .build();
    final String translation = messageBundle.translate(message);
    Assert.assertEquals("Product gewicht 2,35", translation);
  }
}
