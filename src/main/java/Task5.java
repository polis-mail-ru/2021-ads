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

    public static int counter = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int[] temp = new int[n];
        sort(array, temp, 0, array.length - 1);
        System.out.println(counter);
    }

    private static void sort(int[] array, int[] buffer, int i, int k) {
        if (i < k) {
            int mid = (i + k) / 2;
            sort(array, buffer, i, mid);
            sort(array, buffer, mid + 1, k);
            merge(array, buffer, i, mid, k);
        }
    }

    private static void merge(int[] array, int[] buffer, int i, int mid, int k) {
        int first = i;
        int second = mid + 1;
        System.arraycopy(array, i, buffer, i, k - i + 1);

        for (int j = i; j <= k; j++) {
            if (first > mid) {
                array[j] = buffer[second++];
            } else if (second > k) {
                array[j] = buffer[first++];
            }
            else if (buffer[first] <= buffer[second]) {
                array[j] = buffer[first++];
            } else {
                array[j] = buffer[second++];
                counter += mid + 1 - first;
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