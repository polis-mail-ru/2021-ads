import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public final class HomeWork4Task4 {

    private HomeWork4Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] floorCost = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                floorCost[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                floorCost[i][j] += Math.max(floorCost[i - 1][j], floorCost[i][j + 1]);
            }
        }
        int i = n;
        int j = 0;
        while (i != 1 && j != m) {
            if (floorCost[i][j + 1] > floorCost[i - 1][j]) {
                out.print("R");
                j++;
            } else {
                out.print("F");
                i--;
            }
        }
        if (j != m) {
            for (int k = 1; k < m - j; k++) {
                out.print("R");
            }
        } else {
            for (int k = 1; k < n - i; k++) {
                out.print("F");
            }
        }
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