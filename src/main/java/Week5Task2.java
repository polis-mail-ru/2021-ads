import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week5Task2 {
    private Week5Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        out.println(FindAppropriateDiploma(w, h, n, Math.min(w, h), (long) Math.max(w, h) * n));
    }

    private static long FindAppropriateDiploma(int w, int h, int n, long min, long max) {
        if (max <= min) {
            return min;
        }
        long mid = (min + max) / 2;
        long count = (mid / w) * (mid / h);
        if (count >= n) {
            return FindAppropriateDiploma(w, h, n, min, mid);
        } else {
            return FindAppropriateDiploma(w, h, n, mid + 1, max);
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