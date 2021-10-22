
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] arr;

        public Heap() {
            arr = new int[2];
            arr[0] = 1;
        }

        public void insert(int value) {
            if (arr[0] + 1 >= arr.length) {
                arr = Arrays.copyOf(arr, arr.length * 2);
            }
            arr[0]++;
            arr[arr[0]] = value;
            swim(arr[0]);

        }

        private void sink(int index) {
            while (2 * index <= arr[0]) {
                int j = 2 * index;
                if (arr[0] > j && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (arr[j] <= arr[index]) {
                    break;
                }
                swap(index, j);
                index = j;
            }
        }

        private void swap(int i, int j) {
            int buf = arr[i];
            arr[i] = arr[j];
            arr[j] = buf;
        }

        private void swim(int index) {
            while (index > 1 && arr[index] > arr[index / 2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private int deleteMax() {
            int value = arr[1];
            swap(1, arr[0]--);
            sink(1);
            return value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int n = in.nextInt();
        int value;
        for (int i = 0; i < n; i++) {
            value = in.nextInt();
            if (value == 1)
                out.println(heap.deleteMax());
            else
                heap.insert(in.nextInt());
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
