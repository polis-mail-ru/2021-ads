import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task4 {
    private Week2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];

        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
        }

        int[] buffer = new int[array.length];
        sort(array, buffer, 0, array.length);

        int result = 1;
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] != array[i + 1]) {
                ++result;
            }
        }

        out.println(result);
    }

    private static void sort(int[] arr, int[] buffer, int l, int r) {
        if (l == r - 1) {
            return;
        }

        int mid = (r + l) / 2;
        sort(arr, buffer, l, mid);
        sort(arr, buffer, mid, r);
        merge(arr, buffer, l, mid, r);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int j = left;
        int k = mid;

        for (int i = left; i < right; ++i) {
            if (j < mid && k < right) {
                if (arr[j] < arr[k]) {
                    buffer[i] = arr[j];
                    ++j;
                } else {
                    buffer[i] = arr[k];
                    ++k;
                }
            } else if (j < mid) {
                buffer[i] = arr[j];
                ++j;
            } else if (k < right) {
                buffer[i] = arr[k];
                ++k;
            }
        }

        for (int i = left; i < right; ++i) {
            arr[i] = buffer[i];
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
