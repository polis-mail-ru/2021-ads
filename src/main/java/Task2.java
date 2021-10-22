import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int m = in.nextInt();
        final int n = in.nextInt();
        int[][] arr = new int[m][n]; //Reading data
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[m - i - 1][j] = in.nextInt();
            }
        }

        //We are looking for the maximum
        // number of coins that we can collect
        for (int i = 1; i < n; i++) {
            arr[0][i] = arr[0][i] + arr[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            arr[i][0] = arr[i][0] + arr[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i][j] + Math.max(arr[i][j - 1], arr[i - 1][j]);
            }
        }

        int i = m - 1;
        int j = n - 1;
        StringBuilder builder = new StringBuilder(); //Finding optimal path
        while ((i != 0) || (j != 0)) {
            if (i == 0) {
                builder.append("R");
                j--;
            } else if (j == 0) {
                builder.append("F");
                i--;
            } else {
                if (arr[i - 1][j] > arr[i][j - 1]) {
                    builder.append("F");
                    i--;
                } else {
                    builder.append("R");
                    j--;
                }
            }
        }
        out.println(builder.reverse().toString());
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
