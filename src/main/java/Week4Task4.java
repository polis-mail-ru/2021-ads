import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week4Task4 {
    private Week4Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] c = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            c[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] d = new int[n + 2];
        d[0] = 0;
        int localMax = 0;
        for (int i = 1; i < k; i++) {
            d[i] = localMax + c[i];
            localMax = Math.max(d[i], localMax);
        }
        for (int i = k; i <= n + 1; i++) {
            d[i] = localMax + c[i];
            localMax = d[i];
            for (int j = 1; j < k; j++) {
                localMax = Math.max(localMax, d[i - j]);
            }
        }
        out.println(d[n + 1]);
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