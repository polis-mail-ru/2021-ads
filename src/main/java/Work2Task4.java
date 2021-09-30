import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work2Task4 {
    private Work2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        sort(array, 0, n);

        int res = 1;
        int last = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] != last) {
                res++;
                last = array[i];
            }
        }

        out.println(res);
    }


    private static void sort(int[] array, int from, int to) {
        if (from >= to - 1) {
            return;
        }

        int n = partition(array, from, to);
        sort(array, from, n);
        sort(array, n + 1, to);
    }

    private static int partition(int[] array, int from, int to) {
        Random random = new Random();
        int randomIndex = random.nextInt(to - from) + from;
        swap(array, from, randomIndex);

        int pivotal = array[from];
        int i = from;

        for (int j = from + 1; j < to; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }

        swap(array, i, from);
        return i;
    }

    private static void swap(int[] array, int first, int second) {
        int elementToSwap = array[first];
        array[first] = array[second];
        array[second] = elementToSwap;
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
