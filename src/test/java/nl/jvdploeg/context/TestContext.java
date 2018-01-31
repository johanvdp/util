package nl.jvdploeg.context;

public final class TestContext extends Context<TestContext.Content> {

  interface Content {

    String getContent();
  }

  private final Content content = () -> "test";

  public TestContext() {
  }

  @Override
  protected TestContext.Content getContent() {
    return content;
  }
}
