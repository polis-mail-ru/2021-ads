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
        int[] c = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            c[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] d = new int[n + 2];
        d[0] = 0;
        d[1] = c[1];
        int localMax = Math.max(d[0], d[1]);
        for (int i = 2; i <= k; i++) {
            d[i] = localMax + c[i];
            localMax = Math.max(d[i], localMax);
        }
        for (int i = k + 1; i <= n + 1; i++) {
            int[] step = new int[k];
            for (int j = 0; j < k; j++) {
                step[j] = d[i - j - 1];
            }
            if (i <= n) {
                d[i] = FindMax(step) + c[i];
            } else {
                d[i] = FindMax(step);
            }
        }
        out.println(d[n + 1]);
    }

    private static int FindMax(int[] step) {
        int max = step[0];
        for (int i = 1; i < step.length; i++) {
            if (step[i] > max) {
                max = step[i];
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