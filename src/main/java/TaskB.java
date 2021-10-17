import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private static long[][] arr;

    private TaskB() {
        // Should not be instantiated
    }

    private static String getString(int i, int j) {
        long left = getValue(i, j - 1);
        long bottom = getValue(i + 1, j);
        if (bottom == -1 && left == -1) {
            return "";
        } else if (bottom > left) {
            return getString(i + 1, j) + "F";
        }
        return getString(i, j - 1) + "R";
    }

    private static long getValue(int i, int j) {
        if (i == arr.length || j == -1) {
            return -1;
        }
        return arr[i][j];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        arr = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int counter = 0;
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < m - i; j++) {
                if (j == n) {
                    break;
                }
                arr[i + j][j] += Math.max(getValue(i + j + 1, j), getValue(i + j, j - 1));
            }
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                if (i == m) {
                    break;
                }
                arr[i][j + i] += Math.max(getValue(i + 1, j + i), getValue(i, j + i - 1));
            }
        }
        out.println(getString(0, n - 1));
//        for(int i = 0; i < m; i++) {
//            for(int j = 0; j < n; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println("");
//        }

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
}
