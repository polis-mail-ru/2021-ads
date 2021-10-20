import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Brackets {

  private static final int MAX_BRACKETS_NUMBER = 100;

  private Brackets() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    String brackets;
    try {
      brackets = in.reader.readLine();
    } catch (IOException e) {
      return;
    }

    if (brackets == null) {
      out.println("");
      return;
    }

    int[][] d = new int[brackets.length()][brackets.length()];
    fillDynamics(d, brackets);
    String finishedBracketSequence = finishBracketSequence(brackets, d, 0, brackets.length() - 1);
    out.println(finishedBracketSequence);
  }

  private static void fillDynamics(int[][] d, String brackets) {
    for (int i = 0; i < brackets.length(); ++i) {
      d[i][i] = 1;
    }

    for (int j = 1; j < brackets.length(); ++j) {
      for (int i = 0; i < brackets.length() - j; ++i) {
        int m = i + j;
        d[i][m] = MAX_BRACKETS_NUMBER;
        if (isPairBrackets(brackets.charAt(i), brackets.charAt(m))) {
          d[i][m] = d[i + 1][m - 1];
        }
        for (int k = i; k < m; ++k) {
          d[i][m] = Math.min(d[i][m], d[i][k] + d[k + 1][m]);
        }
      }
    }
  }

  private static boolean isPairBrackets(char ch1, char ch2) {
    return (ch1 == '(' && ch2 == ')') || (ch1 == '[' && ch2 == ']');
  }

  private static String finishBracketSequence(String brackets, int[][] d, int i, int j) {
    if (i > j) {
      return "";
    }
    if (i == j) {
      return brackets.charAt(i) == '(' || brackets.charAt(i) == ')'
          ? "()"
          : "[]";
    }

    if (isPairBrackets(brackets.charAt(i), brackets.charAt(j)) && d[i][j] == d[i + 1][j - 1]) {
      return brackets.charAt(i) + finishBracketSequence(brackets, d, i + 1, j - 1) + brackets.charAt(j);
    }

    for (int k = i; k < j; ++k) {
      if (d[i][k] + d[k + 1][j] == d[i][j]) {
        return finishBracketSequence(brackets, d, i, k) + finishBracketSequence(brackets, d, k + 1, j);
      }
    }
    return "";
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
    return new PrintWriter(System.out, false);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
