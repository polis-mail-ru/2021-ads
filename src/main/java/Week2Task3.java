import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task3 {
    private Week2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();

        long result = 0;

        int j = 1;
        int k = 1;

        for (int i = 0; i < x; ++i) {
            long a = (long)Math.pow(j, 2);
            long b = (long)Math.pow(k, 3);

            if (a < b) {
                result = a;
                j++;
            } else if (a == b) {
                result = a;
                j++;
                k++;
            } else {
                result = b;
                k++;
            }
        }

        out.println(result);
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
