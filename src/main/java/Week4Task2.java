import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week4Task2 {
    private Week4Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        long[][] field = new long[100][100];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i < m; i++) {
            field[i][0] += field[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            field[0][j] += field[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                field[i][j] += Math.max(field[i - 1][j], field[i][j - 1]);
            }
        }
        int n1 = n - 1;
        int m1 = m - 1;
        StringBuilder result = new StringBuilder();
        while (n1 != 0 || m1 != 0) {
            field[m1][n1] = -1;
            if (m1 > 0 && n1 > 0) {
                if (field[m1 - 1][n1] > field[m1][n1 - 1]) {
                    m1--;
                    result.append("F");
;                } else {
                    n1--;
                    result.append("R");
                }
            } else if (m1 == 0) {
                n1--;
                result.append("R");
            } else {
                m1--;
                result.append("F");
            }
        }
        out.println(result.reverse());
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
