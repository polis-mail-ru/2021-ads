import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Task4 {
    private Week3Task4() {
        // Should not be instantiated
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        MaxHeap heap = new MaxHeap();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            heap.insert(in.nextInt());
        }
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

class MaxHeap {
    private int[] a;
    private int n;

    public MaxHeap() {
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

    public String print() {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < n; i++) {
            res.append(a[i]).append(" ");
        }
        res.append(a[n]);
        return res.toString();
    }
}
