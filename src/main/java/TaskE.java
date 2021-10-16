import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void heapSort(int[] arr) {
        makeHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            sink(arr, 0, i);
        }
    }

    private static void makeHeap(int[] arr) {
        int n = arr.length;
        for (int k = n / 2 - 1; k >= 0; k--) {
            sink(arr, k, n);
        }
    }

    private static void sink(int[] arr, int k, int n) {
        while (2 * k + 1 < n) {
            int child = 2 * k + 1;
            if (child + 1 < n && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[k] >= arr[child]) {
                break;
            }
            int temp = arr[k];
            arr[k] = arr[child];
            arr[child] = temp;
            k = child;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        for (int i = 0; i < n; i++) {
            out.print(arr[i] + " ");
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
}
