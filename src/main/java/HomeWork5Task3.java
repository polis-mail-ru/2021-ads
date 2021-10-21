import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public final class HomeWork5Task3 {

    private HomeWork5Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] sequence = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = in.nextInt();
        }
        d[0] = 1;
        int dMax;
        for (int i = 1; i < n; i++) {
            dMax = 0;
            for (int j = 0; j < i; j++) {
                if (d[j] > dMax && sequence[j] != 0 && sequence[i] % sequence[j] == 0) {
                    dMax = d[j];
                }
            }
            d[i] = dMax + 1;
        }
        out.println(Arrays.stream(d).max().getAsInt());
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