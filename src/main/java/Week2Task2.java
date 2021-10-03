import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task2 {
    private Week2Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] elements = new int[n];

        for (int i = 0; i < n; ++i) {
            elements[i] = in.nextInt();
        }

        sort(elements);

        for (int i = 0; i < n; ++i) {
            out.print(elements[i] + " ");
        }
    }

    private static void sort(int[] arr) {
        int rangeSize = 107;

        int minElement = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < minElement) {
                minElement = arr[i];
            }
        }

        int[] range = new int[rangeSize];
        for (int i = 0; i < arr.length; ++i) {
            range[arr[i] - minElement]++;
        }

        int indexArr = 0;
        for (int i = 0; i < rangeSize; ++i) {
            while (range[i] > 0) {
                arr[indexArr] = minElement + i;
                indexArr++;
                range[i]--;
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
