import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// D. Разные
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int partition(long[] a, int l, int r) {
        long pivot = a[r], temp;
        int i = (l - 1);

        for (int j = l; j < r; j++) {
            if (a[j] <= pivot) {
                i++;

                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;

        return i + 1;
    }

    public static void sort(long[] a, int l, int r) {
        if (l < r) {
            int i = partition(a, l, r);

            sort(a, l, i - 1);
            sort(a, i + 1, r);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(in.next());
        }

        sort(a, 0, a.length - 1);

        long b = a[0] - 1;
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b) {
                c++;
                b = a[i];
            }
        }

        out.println(c);
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