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
// https://www.e-olymp.com/ru/submissions/9658410
public final class SquareRoot {

  private static double ERROR_WHEN_DOUBLES_COMPARING = 1e-6;

  private SquareRoot() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    double c = in.nextDouble();

    double upperBound = Math.abs(c);
    double lowerBound = 0;
    double x = (upperBound + lowerBound)/2;
    double curFunctionValue = f(x);
    while (Math.abs(c - curFunctionValue) > ERROR_WHEN_DOUBLES_COMPARING) {
      if (curFunctionValue > c) {
        upperBound = x;
      } else {
        lowerBound = x;
      }
      x = (upperBound + lowerBound)/2;
      curFunctionValue = f(x);
    }
    out.println(x);
  }

  private static double f(double x) {
    return x*x + Math.sqrt(x);
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
    double nextDouble() {
      return Double.parseDouble(next());
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
