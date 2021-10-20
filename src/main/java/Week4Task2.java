import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9589719
 */
public final class Week4Task2 {
    private Week4Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] temple = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                temple[i][j] = in.nextInt();
            }
        }

        int[][] dynamic = new int[m][n];
        dynamic[m - 1][0] = temple[m - 1][0];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (i < m - 1 && j > 0) {
                    dynamic[i][j] = temple[i][j] + Math.max(dynamic[i][j - 1], dynamic[i + 1][j]);
                } else if (i == m - 1 && j > 0) {
                    dynamic[i][j] = temple[i][j] + dynamic[i][j - 1];
                } else if (i < m - 1 && j == 0) {
                    dynamic[i][j] = temple[i][j] + dynamic[i + 1][j];
                }
            }
        }

        int i = 0;
        int j = n - 1;
        StringBuilder str = new StringBuilder();
        while (i != m - 1 || j != 0) {
            if (i < m - 1 && j > 0) {
                if (dynamic[i][j - 1] > dynamic[i + 1][j]) {
                    str.append("R");
                    j--;
                } else {
                    str.append("F");
                    i++;
                }
            } else if (i == m - 1 && j > 0) {
                str.append("R");
                j--;
            } else if (i < m - 1 && j == 0) {
                str.append("F");
                i++;
            }
        }

        out.println(str.reverse().toString());
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
