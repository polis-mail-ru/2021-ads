import java.io.*;
import java.util.StringTokenizer;

public final class Week2Task5 {
    private Week2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int[] arrA = new int[m];
        for (int i = 0; i < m; i++) {
            arrA[i] = in.nextInt();
        }
        int n = in.nextInt();
        int[] arrB = new int[n];
        for (int i = 0; i < n; i++) {
            arrB[i] = in.nextInt();
        }

        Sort(arrA, 0, m);
        Sort(arrB, 0, n);

        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (arrA[i] != arrB[j]) {
                System.out.println("NO");
                return;
            }
            while (i < m - 1 && arrA[i] == arrA[i + 1]) {
                i++;
            }
            while (j < n - 1 && arrB[j] == arrB[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        if (i < m || j < n) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
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

    static void Sort(int[] a, int from, int to) {
        if (from == to - 1) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        Sort(a, from, mid);
        Sort(a, mid, to);
        merge(a, from, mid, to);
    }

    static void merge(int[] a, int from, int mid, int to) {
        int i = 0;
        int j = 0;
        int[] b = new int[to - from];
        while (from + i < mid && mid + j < to) {
            if (a[from + i] < a[mid + j]) {
                b[i + j] = a[from + i];
                i++;
            } else {
                b[i + j] = a[mid + j];
                j++;
            }
        }
        while (from + i < mid) {
            b[i + j] = a[from + i];
            i++;
        }
        while (mid + j < to) {
            b[i + j] = a[mid + j];
            j++;
        }
        for (i = 0; i < to - from; i++) {
            a[from + i] = b[i];
        }
    }
}