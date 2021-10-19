import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());
        double min = 0;
        double max = c;
        double mid = (max + min) / 2;

        while (Math.abs(func(mid) - c) > 1e-6) {
            if (func(mid) > c) {
                max = mid;
            } else {
                min = mid;
            }
            mid = (max + min) / 2;
        }

        out.println(mid);
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
