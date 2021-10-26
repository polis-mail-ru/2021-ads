import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1 {
    private static final double EPSILON = 1e-6;

    //https://www.e-olymp.com/ru/submissions/9645796
    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double l = 0;
        double r = c;
        double mid = (l + r) / 2;
        double res = 0;
        while (Math.abs(l - r) > EPSILON) {
            mid = (l + r) / 2;
            res = mid * mid + Math.sqrt(mid);
            if (res > c) {
                r = mid;
            } else {
                l = mid;
            }
        }

        out.println(mid);
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
