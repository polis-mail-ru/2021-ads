import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task3 {
    private Week2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int order = in.nextInt();
        long current = 0;
        int indexOfCurrent = 0;
        long currentA = 1;
        int indexOfCurrentA = 1;
        long currentB = 1;
        int indexOfCurrentB = 1;
        while (indexOfCurrent != order) {
            if (currentA <= currentB) {
                if (currentA == currentB) {
                    indexOfCurrentB++;
                    currentB = (long) Math.pow(indexOfCurrentB, 3);
                }
                current = currentA;
                indexOfCurrent++;
                indexOfCurrentA++;
                currentA = (long) Math.pow(indexOfCurrentA, 2);
            } else {
                current = currentB;
                indexOfCurrent++;
                indexOfCurrentB++;
                currentB = (long) Math.pow(indexOfCurrentB, 3);
            }
        }
        out.println(current);
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
