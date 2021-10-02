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

    public static void merge(long[] a, int l, int mid, int r) {
        long[] aLeft = new long[mid - l + 1];
        long[] aRight = new long[r - mid];

        for (int i = 0; i < aLeft.length; i++) {
            aLeft[i] = a[l + i];
        }

        for (int i = 0; i < aRight.length; i++) {
            aRight[i] = a[mid + i + 1];
        }

        int iLeft = 0, iRight = 0;

        for (int i = l; i < r + 1; i++) {
            if (iLeft < aLeft.length && iRight < aRight.length) {
                if (aLeft[iLeft] < aRight[iRight]) {
                    a[i] = aLeft[iLeft];
                    iLeft++;
                } else {
                    a[i] = aRight[iRight];
                    iRight++;
                }
            } else if (iLeft < aLeft.length) {
                a[i] = aLeft[iLeft];
                iLeft++;
            } else if (iRight < aRight.length) {
                a[i] = aRight[iRight];
                iRight++;
            }
        }
    }

    public static void sort(long[] array, int left, int right) {
        if (right <= left) return;

        int mid = (left + right) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
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