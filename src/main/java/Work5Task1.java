import java.io.*;
import java.util.StringTokenizer;


public class Work5Task1 {
    private static final double epsilon = 0.000001;
    private static void solve(final FastScanner in, final PrintWriter out) {
        double C = Double.parseDouble(in.next());

        double left = 0;
        double right = Math.sqrt(C);

        while (Math.abs(func(left) - C) >= epsilon) {
            double mid = (left + right) / 2;

            if (func(mid) > C) {
                right = mid;
            } else {
                left = mid;
            }
        }
        out.println(left);
    }

    private static double func(double x) {
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
