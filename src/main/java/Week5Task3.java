import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9587944
 */
public final class Week5Task3 {
    private Week5Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = in.nextInt();
        }

        int[] dynamic = new int[N];
        for (int i = 0; i < N; ++i) {
            int tempDynamic = 0;

            for (int j = i; j >= 0; --j) {
                if (tempDynamic < dynamic[j] && array[j] != 0 && array[i] % array[j] == 0) {
                    tempDynamic = dynamic[j];
                }
            }

            dynamic[i] = tempDynamic + 1;
        }

        int result = 0;
        for (int i = 0; i < N; ++i) {
            if (result < dynamic[i]) {
                result = dynamic[i];
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
