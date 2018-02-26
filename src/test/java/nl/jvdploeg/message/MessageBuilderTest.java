package nl.jvdploeg.message;

import org.junit.Assert;
import org.junit.Test;

public class MessageBuilderTest {

  @Test
  public void testBuild() {
    // given
    final MessageBuilder builder = new MessageBuilder("key");
    // when
    final Message message = builder.build();
    // then
    Assert.assertEquals("key", message.getKey());
    Assert.assertNotNull(message.getArguments());
    Assert.assertEquals(0, message.getArguments().size());
  }

  @Test
  public void testBuildWithArguments() {
    // given
    final MessageBuilder builder = new MessageBuilder("key");
    builder.add("arg1", "value1");
    builder.add("arg2", "value2");
    // when
    final Message message = builder.build();
    // then
    Assert.assertEquals("key", message.getKey());
    Assert.assertNotNull(message.getArguments());
    Assert.assertEquals(2, message.getArguments().size());
    Assert.assertEquals("value1", message.getArguments().get("arg1"));
    Assert.assertEquals("value2", message.getArguments().get("arg2"));
  }
}
