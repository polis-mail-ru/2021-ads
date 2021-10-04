import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class MergingSequences {
  private MergingSequences() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int x = in.nextInt();
    if (x == 1) {
      out.println(1);
      return;
    }

    long cx = 0;
    int indexA = 1;
    int indexB = 1;
    for (int i = 0; i < x; ++i) {
      long ai = getAI(indexA);
      long bi = getBI(indexB);
      cx = ai <= bi ? ai : bi;
      indexA += ai <= bi ? 1 : 0;
      indexB += ai >= bi ? 1 : 0;
    }
    out.println(cx);
  }

  private static long getAI(int i) {
    return (long) i * i;
  }

  private static long getBI(int i) {
    return (long) i * i * i;
  }

  public static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner(final InputStream in) {
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

    public int nextInt() {
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
