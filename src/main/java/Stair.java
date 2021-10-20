import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Stair {
  private Stair() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    fillArrayByInput(in, a);
    int k = in.nextInt();
    int maxStairCost = stairCost(a, k);
    out.println(maxStairCost);
  }

  private static void fillArrayByInput(final FastScanner in, int[] a) {
    for (int i = 1; i < a.length; ++i) {
      a[i] = in.nextInt();
    }
  }

  private static int stairCost(int[] c, int k) {
    int[] d = new int[c.length];
    d[1] = c[1];
    for (int i = 2; i < d.length; ++i) {
      int start = Math.max(0, i - k);
      d[i] = maxInInterval(d, start, i) + c[i];
    }
    return maxInInterval(d, d.length - k, d.length);
  }

  private static int maxInInterval(int[] d, int start, int end) {
    int max = d[start];
    for (int i = start + 1; i < end; ++i) {
      max = Math.max(d[i], max);
    }
    return max;
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
