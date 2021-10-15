import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9485340
 */
public final class Week3Task1 {
    private Week3Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt() + 1];
        for(int i = 1; i < array.length; ++i) {
            array[i] = in.nextInt();
        }

        for (int i = 1; i < array.length; ++i) {
            if ((2 * i < array.length && array[i] > array[2 * i]) || (2 * i + 1 < array.length && array[i] > array[2 * i + 1])) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");
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
