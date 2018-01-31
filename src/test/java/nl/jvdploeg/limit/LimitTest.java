package nl.jvdploeg.limit;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LimitTest {

  private Limit<String, Integer> limit;

  @Before
  public void before() {
    final Predicate<String> predicate = t -> t.length() > 3;
    limit = new Limit<>("name", Integer.valueOf(3), predicate, "%1$s %2$s %3$d");
  }

  @Test
  public void testPredicateFalse() {
    Assert.assertEquals("name aaa 3", limit.check("aaa"));
  }

  @Test
  public void testPredicateTrue() {
    Assert.assertEquals(null, limit.check("aaaa"));
  }
}
