package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
// https://www.e-olymp.com/ru/submissions/9660952
public final class Diplomas {
  private Diplomas() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    long w = in.nextInt();
    long h = in.nextInt();
    long n = in.nextInt();

    long lowerBound = Math.max(w, h);
    long upperBound = n*lowerBound;
    while (lowerBound < upperBound) {
      long mid = (lowerBound + upperBound)/2;
      long nDiplomasOnWidth = mid/w;
      long nDiplomasOnHeight = mid/h;
      long curNDiplomas = nDiplomasOnWidth*nDiplomasOnHeight;
      if (curNDiplomas >= n) {
        upperBound = mid;
      } else {
        lowerBound = mid + 1;
      }
    }
    out.println(lowerBound);
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

