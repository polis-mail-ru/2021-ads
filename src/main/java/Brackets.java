import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
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
    String bracketsSequence = in.next();

    int bracketSequenceSize = bracketsSequence.length();
    for (int i = 0; i < bracketSequenceSize; ++i) {
      char curChar = bracketsSequence.charAt(i);
      if ((curChar == OPEN_PARENTHESIS)
          || (curChar == OPEN_BRACKET)
          || (curChar == OPEN_BRACE)) {
        stack.addLast(curChar);
        continue;
      }
      if (curChar == CLOSE_PARENTHESIS) {
        if (stack.getLast() != OPEN_PARENTHESIS) {
          out.println("no");
          return;
        }
      } else if (curChar == CLOSE_BRACKET) {
        if (stack.getLast() != OPEN_BRACKET) {
          out.println("no");
          return;
        }
      } else if (curChar == CLOSE_BRACE) {
        if (stack.getLast() != OPEN_BRACE) {
          out.println("no");
          return;
        }
      } else {
        out.println("no");
        return;
      }
      stack.pollLast();
    }
    if (stack.isEmpty()) {
      out.println("yes");
    } else {
      out.println("no");
    }
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
