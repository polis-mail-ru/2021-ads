import java.io.*;
import java.util.StringTokenizer;

public final class Part2Task3 {
    private Part2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        out.println(getElementOfSortedSequences(2, 3, x));
    }

    public static long getElementOfSortedSequences(int degree1, int degree2, int x) {
        long tmp = 0;
        int ptr1 = 0;
        int ptr2 = 0;
        long seq1;
        long seq2;

        for (int i = 0; i < x + 1; i++) {
            seq1 = (long) Math.pow(ptr1, degree1);
            seq2 = (long) Math.pow(ptr2, degree2);
            tmp = Math.min(seq1, seq2);

            if (seq1 <= tmp) {
                ptr1++;
            }

            if (seq2 <= tmp) {
                ptr2++;
            }
        }
        return tmp;
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
