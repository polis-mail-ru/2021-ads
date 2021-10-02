import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task3 {
    private Part2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long result = 0;
        for (int iInA = 1, iInB = 1, i = 0; i < x; ++i) {
            if (Double.compare(Math.pow(iInA, 2), Math.pow(iInB, 3)) == 0) {
                result = (long) Math.pow(iInA, 2);
                iInA++;
                iInB++;
            } else if (Math.pow(iInA, 2) < Math.pow(iInB, 3)) {
                result = (long) Math.pow(iInA, 2);
                iInA++;
            } else if (Math.pow(iInA, 2) > Math.pow(iInB, 3)) {
                result = (long) Math.pow(iInB, 3);
                iInB++;
            }
        }
        out.println(result);
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