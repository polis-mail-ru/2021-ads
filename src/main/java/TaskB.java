import java.io.*;
import java.util.StringTokenizer;

public class TaskB {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int width = in.nextInt();
        int height = in.nextInt();
        int necCount = in.nextInt();

        long min = Math.max(width, height);
        long max = (long)Math.max(width, height) * necCount;

        while (min < max) {
            long mid = (min + max) / 2;
            long curCount = (mid / width) * (mid / height);
            if (curCount >= necCount) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        out.println(min);
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
