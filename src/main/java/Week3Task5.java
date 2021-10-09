import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Task5 {
    private Week3Task5() {
        // Should not be instantiated
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        HeapSort heap = new HeapSort();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            heap.insert(in.nextInt());
        }
        heap.heapSort();
        out.println(heap.print());
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

class HeapSort {
    private int[] a;
    private int n;

    public HeapSort() {
        a = new int[1000001];
        n = 0;
    }

    private void swap(int i, int j) {
        int buf = a[i];
        a[i] = a[j];
        a[j] = buf;
    }

    private void swim(int k) {
        while (k > 1 && a[k] > a[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void insert(int v) {
        a[++n] = v;
        swim(n);
    }

    private void sink(int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[k] >= a[j]) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void heapSort() {
        int buf = n;
        for (int k = buf / 2; k >= 1; k--) {
            sink(k, buf);
        }
        while (buf > 1) {
            swap(1, buf--);
            sink(1, buf);
        }
    }

    public String print() {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < n; i++) {
            res.append(a[i]).append(" ");
        }
        res.append(a[n]);
        return res.toString();
    }
}