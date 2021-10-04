
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length1 = in.nextInt();
        int[] arr1 = new int[length1];
        for (int j = 0; j < length1; j++) {
            arr1[j] = in.nextInt();
        }
        sort(arr1, 0, length1);
        int length2 = in.nextInt();
        int[] arr2 = new int[length2];
        for (int j = 0; j < length2; j++) {
            arr2[j] = in.nextInt();
        }
        sort(arr2, 0, length2);

        int i = 0, j = 0;
        while (i < length1 && j < length2) {
            if (arr1[i] != arr2[j]) {
                out.println("NO");
                return;
            }
            while ((i + 1 < length1) && arr1[i] == arr1[i + 1]) {
                ++i;
            }
            while ((j + 1 < length2) && arr2[j] == arr2[j + 1]) {
                ++j;
            }
            ++i;
            ++j;
        }
        if (i == length1 && j == length2) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }

    public static void sort(int[] arr, int fromInclusive, int toExclusive) {
        if (fromInclusive + 1 >= toExclusive) {
            return;
        }
        int i = partition(arr, fromInclusive, toExclusive);
        sort(arr, fromInclusive, i);
        sort(arr, i + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static void swap(int[] array, int i, int j) {
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
