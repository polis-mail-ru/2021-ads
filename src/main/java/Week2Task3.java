import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №1442. Объединение последовательностей
 * <p>
 * Memory O(1)
 * <p>
 * Time O(x)
 */
public final class Week2Task3 {
    private Week2Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(squareOrCube(in.nextInt()));
    }

    private static long squareOrCube(int resultIndex) {
        long squareIndex = 0;
        long cubeIndex = 0;
        for (int i = 0; i < resultIndex; i++) {
            long compare = Long.compare(squareIndex * squareIndex, cubeIndex * cubeIndex * cubeIndex);
            if (compare <= 0) {
                squareIndex++;
            }
            if (compare >= 0) {
                cubeIndex++;
            }
        }
        return Math.min(squareIndex * squareIndex, cubeIndex * cubeIndex * cubeIndex);
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
