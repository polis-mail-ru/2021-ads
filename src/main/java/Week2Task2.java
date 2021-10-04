import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №1425. Разброс
 * <p>
 * Memory O(1)
 * <p>
 * Time O(N)
 */
public final class Week2Task2 {
    private Week2Task2() {
        // Should not be instantiated
    }

    final static int MAX_VALUE = 107 + 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] values = new int[in.nextInt()];
        for (int i = 0; i < values.length; i++) {
            values[i] = in.nextInt();
        }
        int minValue = min(values);

        // normalize
        for (int i = 0; i < values.length; i++) {
            values[i] -= minValue;
        }

        countingSort(values);

        for (int value : values) {
            out.print(minValue + value);
            out.print(' ');
        }
    }

    private static int min(int[] array) {
        if (array.length == 0) {
            return Integer.MAX_VALUE;
        }
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < result) {
                result = array[i];
            }
        }
        return result;
    }

    private static void countingSort(int[] array) {
        int[] counts = new int[MAX_VALUE];
        for (int value : array) {
            counts[value]++;
        }
        int j = 0;
        for (int value = 0; value < counts.length; value++) {
            for (int i = 0; i < counts[value]; i++) {
                array[j++] = value;
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
