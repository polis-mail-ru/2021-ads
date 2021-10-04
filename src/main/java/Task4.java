import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[] arr = new int[N];
        int counter = 1;

        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }

        sort(arr, 0, N);

        for (int i = 0; i < N - 1; i++) {
            if (arr[i] < arr[i + 1])
                counter++;
        }
        out.println(counter);

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
