import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private static int[] stairs;
    private static int[] points;
    private static int k;

    private TaskD() {
        // Should not be instantiated
    }

    private static int countPoints(int i) {
        if (i <= 0) {
            return 0;
        }
        if (points[i] != -1001) {
            return points[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 1; j <= k; j++) {
            int temp = countPoints(i - j);
            if (temp > max) {
                max = temp;
            }
        }
        points[i] = stairs[i] + max;
        return points[i];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n <= 0) {
            out.println("0");
            return;
        }
        stairs = new int[n + 2];
        points = new int[n + 2];
        Arrays.fill(points, -1001);
        for (int i = 1; i <= n; i++) {
            stairs[i] = in.nextInt();
        }
        k = in.nextInt();
        out.println(countPoints(n + 1));
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
