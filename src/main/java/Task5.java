import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    static void swap(int[] a, int i, int j) {
        int s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    static boolean nextPermutation(int[] a) {
        int j = a.length - 2;
        while (j != -1 && a[j] >= a[j + 1]) {
            j--;
        }
        if (j == -1) {
            return false;
        }

        int k = a.length - 1;

        while (a[j] >= a[k]) {
            k--;
        }

        swap(a, j, k);
        int l = j + 1;
        int r = a.length - 1;

        while (l < r) {
            swap(a, l++, r--);
        }

        return true;
    }

    static void print(int[] a, final PrintWriter out) {
        for (int i : a)
            out.print(i + " ");
        out.println();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i + 1;
        }
        do {
            print(a, out);
        } while (nextPermutation(a));

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
