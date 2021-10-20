import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9587351
 */
public final class Week5Task1 {
    private Week5Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final double eps = 1E-6;

        double C = Double.parseDouble(in.next());

        double leftBound = 0.0;
        double rightBound = Math.sqrt(C);

        while (Math.abs(leftBound - rightBound) > eps) {
            double mid = (leftBound + rightBound) / 2;
            double x = Math.pow(mid, 2) + Math.sqrt(mid);

            if (x < C) {
                leftBound = mid;
            } else {
                rightBound = mid;
            }
        }

        out.println((leftBound + rightBound) / 2);
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
