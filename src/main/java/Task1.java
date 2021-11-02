import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());
        double left = 0;
        double right = c;
        double x = (left + right) / 2;
        final double EPS = 1e-7;

        while (Math.abs(f(x) - c) > EPS) {
            if (f(x) < c) {
                left = x;
            } else {
                right = x;
            }
            x = (left + right) / 2;
        }

        out.println(x);

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
