import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Task3 {
    private Week3Task3() {
        // Should not be instantiated
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(true);
        Heap minHeap = new Heap(false);
        int num;
        for (int i = 0; i < 1000000; i++) {
            try {
                num = in.nextInt();
            } catch (Exception e) {
                break;
            }
            if (i % 2 == 0) {
                maxHeap.insert(num);
            } else {
                minHeap.insert(num);
            }
            if (minHeap.getN() > 0 && maxHeap.getTop() > minHeap.getTop()) {
                maxHeap.insert(minHeap.delMax());
                minHeap.insert(maxHeap.delMax());
            }
            if (minHeap.getN() > maxHeap.getN()) {
                System.out.println(minHeap.getTop());
            } else if (maxHeap.getN() > minHeap.getN()) {
                System.out.println(maxHeap.getTop());
            } else {
                System.out.println((maxHeap.getTop() + minHeap.getTop()) / 2);
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

class Heap {
    private int[] a;
    private int n;
    private final boolean isMaxHeap;

    public Heap(boolean isMaxHeap) {
        a = new int[1000001];
        n = 0;
        this.isMaxHeap = isMaxHeap;
    }

    private void swap(int i, int j) {
        int buf = a[i];
        a[i] = a[j];
        a[j] = buf;
    }

    private void swim(int k) {
        while (k > 1 && ((isMaxHeap && a[k] > a[k / 2]) || (!isMaxHeap && a[k] < a[k / 2]))) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && ((isMaxHeap && a[j] < a[j + 1]) || (!isMaxHeap && a[j] > a[j + 1]))) {
                j++;
            }
            if ((isMaxHeap && a[k] >= a[j]) || (!isMaxHeap && a[k] <= a[j])) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void insert(int v) {
        a[++n] = v;
        swim(n);
    }

    public int delMax() {
        int max = a[1];
        swap(1, n--);
        sink(1);
        return max;
    }

    public int getTop() {
        return a[1];
    }

    public int getN() {
        return n;
    }
}
