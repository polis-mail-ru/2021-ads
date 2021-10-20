import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class NumberOfInversions {
  private NumberOfInversions() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int N = in.nextInt();
    int[] a = new int[N];
    fillArrayByInput(in, a);
    int nInv = countInv(a, 0, a.length);
    out.println(nInv);
  }

  private static void fillArrayByInput(final FastScanner in, int[] a) {
    for (int i = 0; i < a.length; ++i) {
      a[i] = in.nextInt();
    }
  }

  private static int countInv(int[] a, int i, int j) {
    if (j - i <= 1) {
      return 0;
    }
    int mid = (i + j)/2;
    return countInv(a, i, mid) + countInv(a, mid, j) + countSplitInv(a, i, mid, j);
  }

  private static int countSplitInv(int[] a, int start, int mid, int end) {
    if (a.length <= 1) {
      return 0;
    }
    int nInv = 0;
    int[] result = new int[end - start];

    int left = start;
    int right = mid;
    int count = 0;
    while (left < mid && right < end) {
      if (a[left] > a[right]) {
        nInv += mid - left;
        result[count++] = a[right++];
      } else {
        result[count++] = a[left++];
      }
    }
    while (left < mid) {
      result[count++] = a[left++];
    }
    while (right < end) {
      result[count++] = a[right++];
    }

    System.arraycopy(result, 0, a, start, end - start);
    return nInv;
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
