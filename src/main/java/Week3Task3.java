import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Найти медиану - 2
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] a;
        private int n;
        private final Comparator<Integer> comparator;

        public Heap(Comparator<Integer> comparator) {
            this.n = 0;
            a = new int[9];
            this.comparator = comparator;
        }

        private void doubleArray() {
            a = Arrays.copyOf(a, a.length * 2);
        }

        public void insert(int value) {
            if (n + 2 >= a.length) {
                doubleArray();
            }
            a[++n] = value;
            swim(n);
        }

        public int delTop() {
            int top = a[1];
            swap(1, n--);
            sink(1);
            return top;
        }

        public int top() {
            return a[1];
        }

        private void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && comparator.compare(a[k], a[k / 2]) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && comparator.compare(a[j + 1], a[j]) < 0) j++;
                if (comparator.compare(a[k], a[j]) <= 0) break;
                swap(k, j);
                k = j;
            }
        }

        public int size() {
            return n;
        }
    }

    private static int getMedian(Heap heap1, Heap heap2) {
        if (heap1.size() > heap2.size()) {
            return heap1.top();
        } else if (heap1.size() < heap2.size()) {
            return heap2.top();
        } else {
            return (heap1.top() + heap2.top()) / 2;
        }
    }

    private static void exchangeLastElements(Heap heap1, Heap heap2) {
        int temp = heap1.top();
        heap1.insert(heap2.delTop());
        heap2.insert(temp);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        Heap left = new Heap((o1, o2) -> o2 - o1);
        Heap right = new Heap(Comparator.comparingInt(o -> o));
        while (scanner.hasNext()) {
            int a = scanner.nextInt();

            if (left.size() == 0) {
                left.insert(a);
                out.println(getMedian(left, right));
                continue;
            }

            if (right.size() == 0) {
                right.insert(a);
                out.println(getMedian(left, right));
                continue;
            }

            while (right.top() < left.top()) {
                exchangeLastElements(left, right);
            }

            if (a >= right.top()) {
                if (right.size() > left.size()) {
                    left.insert(right.delTop());
                }
                right.insert(a);
            } else if (a > left.top()) {
                if (left.size() < right.size()) {
                    left.insert(a);
                } else {
                    right.insert(a);
                }
            } else {
                if (left.size() > right.size()) {
                    right.insert(left.delTop());
                }
                left.insert(a);
            }
            out.println(getMedian(left, right));
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