import java.io.*;
import java.util.StringTokenizer;

public class Work4Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] ladder = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            ladder[i] = in.nextInt();
        }

        int k = in.nextInt();

        int[] maxScore = new int[n + 2];

        for (int i = 1; i < n + 2; i++) {
            int maxPrevScore = maxScore[i - 1];
            for (int j = 2; j <= k; j++) {
                if (i - j < 0) {
                    continue;
                }
                if (maxScore[i - j] > maxPrevScore) {
                    maxPrevScore = maxScore[i - j];
                }
            }

            maxScore[i] = maxPrevScore + ladder[i];
        }

        out.println(maxScore[n + 1]);
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
