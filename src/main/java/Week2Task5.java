import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// E. Похожие массивы
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
        long[] a1 = new long[n];

        ArrayList<Long> a1Final = new ArrayList<>();
        ArrayList<Long> a2Final = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a1[i] = Long.parseLong(in.next());
        }

        n = in.nextInt();
        long[] a2 = new long[n];

        for (int i = 0; i < n; i++) {
            a2[i] = Long.parseLong(in.next());
        }

        sort(a1, 0, a1.length - 1);
        sort(a2, 0, a2.length - 1);

        long b = a1[0] - 1;
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != b) {
                a1Final.add(a1[i]);
                b = a1[i];
            }
        }

        b = a2[0] - 1;
        for (int i = 0; i < a2.length; i++) {
            if (a2[i] != b) {
                a2Final.add(a2[i]);
                b = a2[i];
            }
        }

        System.out.println(a1Final.equals(a2Final) ? "YES" : "NO");
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