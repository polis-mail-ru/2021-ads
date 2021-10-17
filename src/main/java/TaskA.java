import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        // elems[0] won't be used
        int[] elems = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            elems[i] = in.nextInt();
        }

        for (int i = 1; i <= n / 2; i++) {
            if((2 * i <= n && elems[i] > elems[2 * i]) || (2 * i + 1 <= n && elems[i] > elems[2 * i + 1])) {
                    out.println("NO");
                    return;
            }
        }
        out.println("YES");
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
