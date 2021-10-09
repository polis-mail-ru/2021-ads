import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Task2 {

    private static int n = 0;

    private Week3Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] a = new int[100001];
        for (int i = 0; i < N; i++) {
            int command = in.nextInt();
            if (command == 0) {
                int num = in.nextInt();
                insert(a, num);
            } else {
                out.println(delMax(a));
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int buf = a[i];
        a[i] = a[j];
        a[j] = buf;
    }

    private static void swim(int[] a, int k) {
        while (k > 1 && a[k] > a[k / 2]) {
            swap(a, k, k / 2);
            k = k / 2;
        }
    }

    private static void sink(int[] a, int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[k] >= a[j]) {
                break;
            }
            swap(a, k, j);
            k = j;
        }
    }

    private static void insert(int[] a, int v) {
        a[++n] = v;
        swim(a, n);
    }

    private static int delMax(int[] a) {
        int max = a[1];
        swap(a, 1, n--);
        sink(a, 1);
        return max;
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