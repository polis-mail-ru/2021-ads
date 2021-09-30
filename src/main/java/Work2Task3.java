import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work2Task3 {
    private Work2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();

        long res = 1;

        int i = 1, j = 1;
        for (int k = 0; k < x; k++) {
            long nextSquare = (long) i * i;
            long nextCube = (long) j * j * j;
            if (nextSquare == nextCube) {
                res = nextCube;
                i++;
                j++;
            } else if (nextSquare < nextCube) {
                res = nextSquare;
                i++;
            } else {
                res = nextCube;
                j++;
            }
        }

        out.println(res);
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
