import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class LCS {
  private LCS() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    fillArrayByInput(in, a);

    int m = in.nextInt();
    int[] b = new int[m + 1];
    fillArrayByInput(in, b);

    int lcs = lcs(a, b);
    out.println(lcs);
  }

  private static void fillArrayByInput(final FastScanner in, int[] a) {
    for (int i = 1; i < a.length; ++i) {
      a[i] = in.nextInt();
    }
  }

  private static int lcs(int[] a, int[] b) {
    int[][] lcs = new int[a.length][b.length];

    for (int i = 1; i < a.length; ++i) {
      for (int j = 1; j < b.length; ++j) {
        if (a[i] == b[j]) {
          lcs[i][j] = lcs[i - 1][j - 1] + 1;
        } else {
          lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
        }
      }
    }

    return lcs[a.length - 1][b.length - 1];
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
