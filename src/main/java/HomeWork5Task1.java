import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork5Task1 {

    private static final double EPS = 1e-6;

    private HomeWork5Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double x1 = 1;
        double x2 = c;
        double mid = (x1 + x2) / 2.0;
        while (Math.abs(x1 - x2) > EPS) {
            if (f(mid) > c) {
                x2 = mid;
            } else {
                x1 = mid;
            }
            mid = (x1 + x2) / 2.0;
        }
        out.println(mid);
    }

    static double f(double x) {
        return x * x + Math.sqrt(x);
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

        double nextDouble() {
            return Double.parseDouble(next());
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