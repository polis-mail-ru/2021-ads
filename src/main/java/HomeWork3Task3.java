import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public final class HomeWork3Task3 {

    private HomeWork3Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        HeapMax heapMax = new HeapMax();
        HeapMin heapMin = new HeapMin();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            if (number < heapMax.getMax()) {
                heapMin.insert(heapMax.removeMax());
                heapMax.insert(number);
            } else {
                heapMin.insert(number);
            }
            if (heapMin.size() - 1 > heapMax.size()) {
                heapMax.insert(heapMin.removeMin());
                out.println((heapMax.getMax() + heapMin.getMin()) / 2);
            } else {
                out.println(heapMin.getMin());
            }
        }
    }


    private static class HeapMax {
        private final int MAX_SIZE = 500_002;
        private final int[] a = new int[MAX_SIZE];
        private int n = 0;

        void insert(int v) {
            a[++n] = v;
            swim(n);
        }

        int removeMax() {
            int max = a[1];
            swap(a, 1, n--);
            sink(1);
            return max;
        }

        public int getMax() {
            return a[1];
        }

        void swim(int k) {
            while (k > 1 && a[k] > a[k / 2]) {
                swap(a, k, k / 2);
                k = k / 2;
            }
        }

        void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && a[j] < a[j + 1]) j++;
                if (a[k] >= a[j]) break;
                swap(a, k, j);
                k = j;
            }
        }

        private void swap(int[] a, int k, int j) {
            int temp = a[k];
            a[k] = a[j];
            a[j] = temp;
        }

        public int size() {
            return n;
        }
    }

    private static class HeapMin {
        private final int MAX_SIZE = 500_002;
        private final int[] a = new int[MAX_SIZE];
        private int n = 0;

        void insert(int v) {
            a[++n] = v;
            swim(n);
        }

        int removeMin() {
            int min = a[1];
            swap(a, 1, n--);
            sink(1);
            return min;
        }

        public int getMin() {
            return a[1];
        }

        void swim(int k) {
            while (k > 1 && a[k] < a[k / 2]) {
                swap(a, k, k / 2);
                k = k / 2;
            }
        }

        void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k; // left child
                if (j < n && a[j] > a[j + 1]) j++; //right
                if (a[k] <= a[j]) break; // invariant
                swap(a, k, j);
                k = j;
            }
        }

        private void swap(int[] a, int k, int j) {
            int temp = a[k];
            a[k] = a[j];
            a[j] = temp;
        }

        public int size() {
            return n;
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

        boolean hasNext() {
            return tokenizer == null || tokenizer.hasMoreElements();
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