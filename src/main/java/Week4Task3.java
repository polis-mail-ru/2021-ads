import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week4Task3 {
    private static int[][] array;
    private static int[] x;
    private static int[] y;

    private Week4Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        x = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            x[i] = in.nextInt();
        }
        int m = in.nextInt();
        y = new int[m + 1];
        for (int j = 1; j <= m; j++) {
            y[j] = in.nextInt();
        }
        array = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                array[i][j] = -1;
            }
        }
        out.println(LongestSubsequence(n, m));
    }

    private static int LongestSubsequence(int n, int m) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (array[n][m] != -1) {
            return array[n][m];
        }
        if (x[n] == y[m]) {
            return array[n][m] = 1 + LongestSubsequence(n - 1, m - 1);
        } else {
            return array[n][m] = Math.max(LongestSubsequence(n, m - 1), LongestSubsequence(n - 1, m));
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
