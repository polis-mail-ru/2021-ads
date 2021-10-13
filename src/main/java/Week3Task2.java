import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Хипуй!
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] a;
        private int n;

        public Heap() {
            this.n = 1;
            a = new int[9];
        }

        private void doubleArray() {
            a = Arrays.copyOf(a, a.length * 2);
        }

        public void insert(int value) {
            if (n + 1 >= a.length) {
                doubleArray();
            }
            a[++n] = value;
            swim(n);
        }

        public int delMax() {
            int max = a[1];
            swap(1, n--);
            sink(1);
            return max;
        }

        private void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && a[k] > a[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && a[j] < a[j + 1]) j++;
                if (a[k] >= a[j]) break;
                swap(k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int n = in.nextInt();
        String s;
        for (int i = 0; i < n; i++) {
            s = in.next();
            if (s.equals("0")) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.delMax());
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