import java.io.*;
import java.util.StringTokenizer;


public class Work5Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        boolean[] init = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            init[i] = true;
        }

        permutations(0, n, init, new int[n], out);
    }

    private static void permutations(int i, int n, boolean[] needToAdd, int[] result, final PrintWriter out) {
        for (int j = 1; j <= n; j++) {
            if (needToAdd[j]) {
                result[i] = j;

                if (i + 1 == n) {
                    for (int number : result) {
                        out.print(number + " ");
                    }
                    out.println("");
                    continue;
                }

                needToAdd[j] = false;
                permutations(i + 1, n, needToAdd, result, out);
                needToAdd[j] = true;
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
