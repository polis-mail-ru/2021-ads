import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week5Task1 {
    private Week5Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());
        out.println(BinarySearch(0, Math.sqrt(c), c));
    }

    private static double BinarySearch(double min, double max, double c) {
        double mid = (min + max) / 2;
        double f = Math.pow(mid, 2) + Math.sqrt(mid);
        if (Math.abs(f - c) < Math.pow(10, -6)) {
            return mid;
        }
        if (f < c) {
            return BinarySearch(mid, max, c);
        } else {
            return BinarySearch(min, mid, c);
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