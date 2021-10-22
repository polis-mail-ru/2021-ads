import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week5Task3 {
    private Week5Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(FindMaxSubsequence(n, a));
    }

    private static int FindMaxSubsequence(int n, int[] a) {
        int[] d = new int[n];
        d[0] = 1;
        int prevMax;
        int max = d[0];
        for (int i = 1; i < n; i++) {
            prevMax = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && d[j] > prevMax) {
                    prevMax = d[j];
                }
            }
            d[i] = prevMax + 1;
            if (d[i] > max) {
                max = d[i];
            }
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
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
