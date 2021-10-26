import java.io.*;
import java.util.StringTokenizer;

public class task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        long r = Math.min(w, h);

        while ((r / w) * (r / h) < n) {
            r *= 2;
        }
        long l = r / 2;
        long mid;
        while (l + 1 < r) {
            mid = (l + r) / 2;
            if ((mid / w) * (mid / h) < n) {
                l = mid;
            } else {
                r = mid;
            }
        }
        out.print(r);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
