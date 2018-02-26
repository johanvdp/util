package nl.jvdploeg.regex;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

  @Test
  public void testGroup() {
    Assert.assertEquals("(x)", Regex.group("x"));
  }

  @Test
  public void testNegativeLookahead() {
    Assert.assertEquals("(?!x)", Regex.negativeLookahead("x"));
  }

  @Test
  public void testNegativeLookbehind() {
    Assert.assertEquals("(?<!x)", Regex.negativeLookbehind("x"));
  }

  @Test
  public void testNot() {
    Assert.assertEquals("^x", Regex.not("x"));
  }

  @Test
  public void testOneOrMore() {
    Assert.assertEquals("x+", Regex.oneOrMore("x"));
  }

  @Test
  public void testOr() {
    Assert.assertEquals("[x]", Regex.or("x"));
  }
}
