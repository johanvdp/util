// The author disclaims copyright to this source code.
package nl.jvdploeg.exception;

import org.junit.Assert;
import org.junit.Test;

import nl.jvdploeg.object.Instance;

public class ThrowableBuilderTest {

  private static final Throwable CAUSE1 = new Throwable();
  private static final Throwable CAUSE2 = new Throwable();
  private static final Object OBJECT1 = new Object();
  private static final Object OBJECT2 = new Object();

  @Test
  public void testAll() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.cause(CAUSE2);
    b.identity("object2", OBJECT2);
    b.message("message2");
    b.method("methodName2");
    b.field("fieldName2", "fieldValue2");
    b.cause(CAUSE1);
    b.identity("object1", OBJECT1);
    b.message("message1");
    b.method("methodName1");
    b.field("fieldName1", "fieldValue1");
    final RuntimeException e = b.build();
    // then
    // one exception is build
    // with message
    // - one method name, the last provided
    // - all messages, in the order provided
    // - all identity and fields, in the order provided
    Assert.assertEquals("methodName1" //
        + ";message2" //
        + ";message1" //
        + ";object2=" + Instance.identity(OBJECT2) //
        + ";fieldName2=fieldValue2" //
        + ";object1=" + Instance.identity(OBJECT1) //
        + ";fieldName1=fieldValue1", //
        e.getMessage());
    // with cause
    // - one cause, the last provided
    Assert.assertEquals(CAUSE1, e.getCause());
  }

  @Test
  public void testCause() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.cause(CAUSE1);
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("", e.getMessage());
    Assert.assertEquals(CAUSE1, e.getCause());
  }

  @Test
  public void testEmpty() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("", e.getMessage());
    Assert.assertEquals(null, e.getCause());
  }

  @Test
  public void testField() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.field("fieldName", "fieldValue");
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("fieldName=fieldValue", e.getMessage());
    Assert.assertEquals(null, e.getCause());
  }

  @Test
  public void testIdentity() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.identity("object1", OBJECT1);
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("object1=" + Instance.identity(OBJECT1), e.getMessage());
    Assert.assertEquals(null, e.getCause());
  }

  @Test
  public void testMessage() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.message("message");
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("message", e.getMessage());
    Assert.assertEquals(null, e.getCause());
  }

  @Test
  public void testMethod() {
    // given
    final ThrowableBuilder<RuntimeException> b = ThrowableBuilder.createRuntimeExceptionBuilder();
    // when
    b.method("methodName");
    final RuntimeException e = b.build();
    // then
    Assert.assertEquals("methodName", e.getMessage());
    Assert.assertEquals(null, e.getCause());
  }
}
