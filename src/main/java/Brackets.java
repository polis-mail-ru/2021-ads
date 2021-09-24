import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Brackets {
  private static final char OPEN_PARENTHESIS = '(', CLOSE_PARENTHESIS = ')',
    OPEN_BRACKET = '[', CLOSE_BRACKET = ']', OPEN_BRACE = '{', CLOSE_BRACE = '}';

  private Brackets() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    LinkedList<Character> stack = new LinkedList<>();
    Scanner input = new Scanner(System.in);
    String bracketSequence = input.hasNext() ? input.next() : "";
    boolean isRightBracketSequence = true;

    for (int position = 0; position < bracketSequence.length(); ++position) {
      Character curChar = bracketSequence.charAt(position);
      if (isOpenBracket(curChar)) {
        stack.addLast(curChar);
        continue;
      }

      if (stack.isEmpty()) {
        isRightBracketSequence = false;
        break;
      }

      Character lastOpenBracket = stack.pollLast();
      boolean isRightParenthesisPair = isParenthesisPair(curChar, lastOpenBracket),
          isRightBracketsPair = isBracketsPair(curChar, lastOpenBracket),
          isRightBracesPair = isBracesPair(curChar, lastOpenBracket);
      if (isRightParenthesisPair || isRightBracketsPair || isRightBracesPair) {
        continue;
      }
      isRightBracketSequence = false;
      break;
    }
    out.println((isRightBracketSequence && stack.isEmpty()) ? "yes" : "no");
  }

  private static boolean isOpenBracket(Character ch) {
    return (ch.equals(OPEN_PARENTHESIS) || ch.equals(OPEN_BRACKET) || ch.equals(OPEN_BRACE));
  }

  private static boolean isParenthesisPair(Character ch1, Character ch2) {
    return (ch1.equals(CLOSE_PARENTHESIS) && ch2.equals(OPEN_PARENTHESIS));
  }

  private static boolean isBracketsPair(Character ch1, Character ch2) {
    return (ch1.equals(CLOSE_BRACKET) && ch2.equals(OPEN_BRACKET));
  }

  private static boolean isBracesPair(Character ch1, Character ch2) {
    return (ch1.equals(CLOSE_BRACE) && ch2.equals(OPEN_BRACE));
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static PrintWriter createPrintWriterForLocalTests() {
    return new PrintWriter(System.out, true);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
