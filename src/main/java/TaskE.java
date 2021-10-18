import java.io.*;
import java.util.StringTokenizer;

public class TaskE {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
    }

    private static int countInv(int[] array, int beg, int end) {
        if (beg - end <= 1) {
            return 0;
        }

        int mid = (beg + end) / 2;
        return countInv(array, beg, mid) + countInv(array, mid + 1, end) + countSplitInv(array, beg, end, mid);

    }

    private static int countSplitInv(int[] array, int beg, int end, int mid) {

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
