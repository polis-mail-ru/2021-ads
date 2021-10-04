import java.io.*;
import java.util.StringTokenizer;

public class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        int numA = 1;
        int numB = 1;
        long cX = 1;

        // aI = (numA)^2, bI = (numB)^2
        for (int j = 0; j < x; j++) {
            long aI = (long) Math.pow(numA, 2);
            long bI = (long) Math.pow(numB, 3);
            if (aI < bI) {
                cX = aI;
                numA++;
            } else if (bI < aI) {
                cX = bI;
                numB++;
            } else {
                cX = aI;
                numA++;
                numB++;
            }
        }

        out.println(cX);
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
