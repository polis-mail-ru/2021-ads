import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public final class HomeWork3Task4 {

    private HomeWork3Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        makeHeap(a);
        for (int i = 1; i < a.length; i++) {
            out.print(a[i] + " ");
        }
    }

    private static void makeHeap(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
    }

    private static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) j++;
            if (a[k] >= a[j]) break;
            swap(a, k, j);
            k = j;
        }
    }

    private static void swap(int[] a, int k, int j) {
        int temp = a[k];
        a[k] = a[j];
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