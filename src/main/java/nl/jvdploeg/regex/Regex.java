// The author disclaims copyright to this source code.
package nl.jvdploeg.regex;

/** A few useful regex expressions. */
public abstract class Regex {

  public static String group(final String string) {
    return "(" + string + ")";
  }

  public static String negativeLookahead(final String string) {
    return "(?!" + string + ")";
  }

  public static String negativeLookbehind(final String string) {
    return "(?<!" + string + ")";
  }

  public static String not(final String string) {
    return "^" + string;
  }

  public static String oneOrMore(final String string) {
    return string + "+";
  }

  public static String or(final String string) {
    return "[" + string + "]";
  }

  private Regex() {
  }
}
