import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork2Task2 {
    private HomeWork2Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int MAX_NUMBERS_DISTANCE = 107;
        int n = in.nextInt();
        int[] numbers = new int[n];
        int minNumber = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            numbers[i] = in.nextInt();
            if (numbers[i] < minNumber) {
                minNumber = numbers[i];
            }
        }
        int[] numberCounters = new int[MAX_NUMBERS_DISTANCE];
        for (int i = 0; i < n; i++) {
            ++numberCounters[numbers[i] - minNumber];
        }
        for (int i = 0; i < MAX_NUMBERS_DISTANCE; i++) {
            for (int j = 0; j < numberCounters[i]; j++) {
                out.print(i + minNumber + " ");
            }
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