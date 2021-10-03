import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// B. Разброс
public final class Main {
    private Main() {
        // Should not be instantiated
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];

        long min = 2_000_000_000, max = -min;

        for (int i = 0; i < a.length; i++) {
            a[i] = Long.parseLong(in.next());
            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > max) {
                max = a[i];
            }
        }

        int[] b = new int[(int)(max - min + 1)];
        for (int i = 0; i < a.length; i++) {
            b[(int)(a[i] - min)] += 1;
        }

        int j = 0;

        for (int i = 0; i < b.length; i++) {
            for (int l = 0; l < b[i]; l++) {
                a[j] = i + min;
                j++;
            }
        }

        for (long l : a) {
            System.out.print(l + " ");
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