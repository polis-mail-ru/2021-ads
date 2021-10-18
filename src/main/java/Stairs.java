import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9570582
public class Stairs {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairs = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            stairs[i] = in.nextInt();
        }
        int stepSize = in.nextInt();
        for (int i = 1; i <= n + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i - 1; j >= i - stepSize; j--) {
                if (j >= 0 && stairs[j] > max) {
                    max = stairs[j];
                }
            }
            stairs[i] = stairs[i] + max;
        }
        out.println(stairs[n + 1]);
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
