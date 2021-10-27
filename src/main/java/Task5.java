import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task5 {
    public static int n;

    // https://www.e-olymp.com/ru/submissions/9661609
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int[] res = new int[n];
        permutations(res, 0);

    }

    public static void permutations(int res[], int i) {
        if (i == n) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[j] + " ");
            }
            System.out.println();
            return;
        }
        for (int j = 1; j <= n; j++) {
            boolean has = false;
            for (int k = 0; k < i; k++) {
                if (res[k] == j) {
                    has = true;
                    break;
                }
            }
            if (has)
                continue;
            res[i] = j;
            permutations(res, i + 1);
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
