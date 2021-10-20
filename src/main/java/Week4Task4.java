import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9597405
 */
public final class Week4Task4 {
    private Week4Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        int[] stairs = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            stairs[i] = in.nextInt();
        }

        int k = in.nextInt();

        int[] dynamic = new int[N + 2];

        int maxDynamic = 0;
        for (int i = 1; i < N + 2; i++) {
            for (int j = 1; j <= k && i - j >= 0; j++) {
                if (maxDynamic < dynamic[i - j]) {
                    maxDynamic = dynamic[i - j];
                }
            }
            dynamic[i] = stairs[i] + maxDynamic;
            maxDynamic = dynamic[i];
        }

        out.println(dynamic[N + 1]);
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
