import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int indexToFind = in.nextInt();
        int aIndex = 1;
        int bIndex = 1;
        long currentA = (long) Math.pow(aIndex, 2);
        long currentB = (long) Math.pow(bIndex, 3);
        for (int i = 1; i < indexToFind; i++) {
            if (currentA < currentB) {
                aIndex++;
                currentA = (long) Math.pow(aIndex, 2);
            } else if (currentA > currentB) {
                bIndex++;
                currentB = (long) Math.pow(bIndex, 3);
            } else {
                aIndex++;
                bIndex++;
                currentA = (long) Math.pow(aIndex, 2);
                currentB = (long) Math.pow(bIndex, 3);
            }
        }
        out.println(Math.min(currentA, currentB));
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
