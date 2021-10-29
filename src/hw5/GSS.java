package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import utils.Utils;

/**
 * Problem solution template.
 */
// https://www.e-olymp.com/ru/submissions/9664157
public class GSS {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    fillArrayByInput(in, a);

    int[] d = new int[n + 1];

    for (int i = 0; i < a.length; ++i) {
      int max = 0;
      for (int j = 1; j < i; ++j) {
        if (a[j] == 0) {
          continue;
        }
        if (isMultiple(a[i], a[j]) && d[j] > max) {
          max = d[j];
        }
      }
      d[i] = max + 1;
    }

    int GSSLength = Utils.maxInInterval(d, 0, d.length);
    out.println(GSSLength);
  }

  private static void fillArrayByInput(final FastScanner in, int[] a) {
    for (int i = 1; i < a.length; ++i) {
      a[i] = in.nextInt();
    }
  }

  private static boolean isMultiple(int a, int b) {
    return  b != 0 && a % b == 0;
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