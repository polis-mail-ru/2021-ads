import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork4Task3 {

    private HomeWork4Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] b = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = in.nextInt();
        }
        int[] pred;
        int[] current = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            pred = current;
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    current[j] = pred[j - 1] + 1;
                } else {
                    current[j] = Math.max(current[j - 1], pred[j]);
                }
            }
        }
        out.println(current[m]);
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