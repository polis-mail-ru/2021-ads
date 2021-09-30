import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work2Task2 {
    private Work2Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int min = Integer.MAX_VALUE;

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            int current = in.nextInt();

            if (current < min) {
                min = current;
            }

            array[i] = current;
        }

        countSort(array, min, n, 107);

        for (int i = 0; i < n; i++) {
            out.print(array[i] + " ");
        }
    }

    private static void countSort(int[] array, int minElem, int size, int maxDifference) {
        int[] res = new int[maxDifference + 1];

        for (int i = 0; i < size; i++) {
            res[array[i] - minElem] += 1;
        }

        int n = 0;
        for (int i = 0; i < maxDifference + 1; i++) {
            while (res[i] > 0) {
                res[i] -= 1;
                array[n++] = i + minElem;
            }
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
