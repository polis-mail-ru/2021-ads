import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9609168
 */
public final class Week5Task5 {
    private Week5Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }

        for (int i = 0; i < n; ++i) {
            out.print(array[i] + " ");
        }
        out.println();

        while (true) {
            int maxJ = 0;
            for (int j = 0; j < n - 1; ++j) {
                if (array[j] < array[j + 1]) {
                    maxJ = j;
                }
            }

            int maxL = 0;
            for (int l = maxJ; l < n; ++l) {
                if (array[l] > array[maxJ]) {
                    maxL = l;
                }
            }

            if (maxL == maxJ) {
                break;
            }

            swap(array, maxJ, maxL);
            reverse(array, maxJ + 1, n);

            for (int i = 0; i < n; ++i) {
                out.print(array[i] + " ");
            }
            out.println();
        }
    }

    private static void reverse(int[] array, int l, int r) {
        for (int i = l; i < (r + l) / 2; ++i) {
            swap(array, i, r + l - i - 1);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
