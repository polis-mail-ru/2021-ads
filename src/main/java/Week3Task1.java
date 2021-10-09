import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Task1 {
    private Week3Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        out.println(ifHeap(a) ? "YES" : "NO");
    }

    private static boolean ifHeap(int[] a) {
        int n = a.length - 1;
        for (int i = 1; i <= n / 2; i++) {
            if (2 * i <= n && a[2 * i] < a[i]) {
                return false;
            }
            if (2 * i + 1 <= n && a[2 * i + 1] < a[i]) {
                return false;
            }
        }
        return true;
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
