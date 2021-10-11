import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9508195
public class HeapRealization {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        MyHeap heap = new MyHeap(n);
        for (int i = 0; i < n; i++) {
            switch (in.nextInt()) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
            }
        }
    }

    private static class MyHeap {
        private int n;
        private final int[] a;

        public MyHeap(int maxSize) {
            n = 0;
            a = new int[maxSize + 1];
        }

        public void insert(int number) {
            a[++n] = number;
            swim(n);
        }

        public int extract() {
            int max = a[1];
            swap(1, n--);
            sink(1);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && a[k] > a[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int child = 2 * k;
                if (child + 1 <= n && a[child + 1] > a[child]) {
                    child = child + 1;
                }
                if (a[k] >= a[child]) {
                    break;
                }
                swap(k, child);
                k = child;
            }
        }

        private void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
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