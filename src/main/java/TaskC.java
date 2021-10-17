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
        int firstSize = in.nextInt();
        int[] arrFirst = new int[firstSize];
        for (int i = 0; i < firstSize; i++) {
            arrFirst[i] = in.nextInt();
        }
        int secondSize = in.nextInt();
        int[] arrSecond = new int[secondSize];
        for (int i = 0; i < secondSize; i++) {
            arrSecond[i] = in.nextInt();
        }
        if (arrFirst.length == 0 || arrSecond.length == 0) {
            return;
        }
        int[][] subStrings = new int[firstSize + 1][secondSize + 1];
        for (int i = 1; i <= firstSize; i++) {
            for (int j = 1; j <= secondSize; j++) {
                if (arrFirst[i - 1] == arrSecond[j - 1]) {
                    subStrings[i][j] = subStrings[i - 1][j - 1] + 1;
                } else {
                    subStrings[i][j] = Math.max(subStrings[i - 1][j], subStrings[i][j - 1]);
                }
            }
        }
        out.println(subStrings[firstSize][secondSize]);
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
}
