package nl.jvdploeg.context;

public final class CountingContext extends Context<CountingContext> {

  private int count;

  public CountingContext() {
  }

  @Override
  public void enter() {
    super.enter();
    count++;
  }

  public int getCount() {
    return count;
  }
}
