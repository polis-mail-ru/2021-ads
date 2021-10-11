import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9507641#results
public class MaxHeap {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] heap = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            heap[i] = in.nextInt();
        }
        makeHeap(heap);
        for (int i = 1; i <= n; i++) {
            out.println(heap[i]);
        }
    }

    private static void makeHeap(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k);
        }
    }

    private static void sink(int[] a, int k) {
        int n = a.length - 1;
        while (2 * k <= n) {
            int child = 2 * k;
            if (child + 1 <= n && a[child + 1] > a[child]) {
                child = child + 1;
            }
            if (a[k] >= a[child]) {
                break;
            }
            swap(a, k, child);
            k = child;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
