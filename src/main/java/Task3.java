import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size];
        int[] d = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        int max = 1;
        d[0] = 1;

        for (int i = 1; i < size; i++) {
            d[i] = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] != 0 && array[i] % array[j] == 0 && d[j] > d[i]) {
                    d[i] = d[j];
                }
            }
            d[i]++;
            max = Math.max(max, d[i]);
        }

        out.println(max);
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
