import java.io.*;
import java.util.StringTokenizer;

public class Work4Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] matrix = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        int[][] maxSeeds = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j < m + 1; j++) {
                maxSeeds[i][j] = Math.max(maxSeeds[i + 1][j], maxSeeds[i][j - 1]) + matrix[i][j];
            }
        }

        int i = 0;
        int j = m;
        StringBuilder res = new StringBuilder();
        while (true) {
            if (i == n - 1) {
                for (int k = 1; k < j; k++) {
                    res.append("R");
                }
                break;
            } else if (j == 1) {
                for (int k = n - 1; k > i; k--) {
                    res.append("F");
                }
                break;
            }

            if (maxSeeds[i + 1][j] == maxSeeds[i][j] - matrix[i][j]) {
                res.append("F");
                i++;
            } else {
                res.append("R");
                j--;
            }
        }

        out.println(res.reverse());
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
