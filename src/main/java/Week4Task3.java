import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9598562
 */
public final class Week4Task3 {
    private Week4Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N1 = in.nextInt();
        int[] firstSeq = new int[N1];
        for (int i = 0; i < N1; ++i) {
            firstSeq[i] = in.nextInt();
        }

        int N2 = in.nextInt();
        int[] secondSeq = new int[N2];
        for (int i = 0; i < N2; ++i) {
            secondSeq[i] = in.nextInt();
        }

        int[][] dynamic = new int[N1 + 1][N2 + 1];
        for (int i = 0; i < N1; ++i) {
            for (int j = 0; j < N2; ++j) {
                if (firstSeq[i] == secondSeq[j]) {
                    dynamic[i + 1][j + 1] = dynamic[i][j] + 1;
                } else {
                    if (dynamic[i][j + 1] > dynamic[i + 1][j]) {
                        dynamic[i + 1][j + 1] = dynamic[i][j + 1];
                    } else {
                        dynamic[i + 1][j + 1] = dynamic[i + 1][j];
                    }
                }
            }
        }

        out.println(dynamic[N1][N2]);
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
