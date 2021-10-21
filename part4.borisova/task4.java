import java.io.*;
import java.util.StringTokenizer;

public class task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] ladder = new int[n + 2];
        ladder[0] = 0;
        ladder[n + 1] = 0;

        for (int i = 1; i < n + 1; i++) {
            ladder[i] = in.nextInt();
        }
        int step = in.nextInt();
        int[] counts = new int[n + 2];
        int maxCount;
        counts[0] = ladder[0];
        for (int i = 1; i < n + 2; i++) {
            counts[i] = ladder[i];
            maxCount = counts[i - 1];
            for (int j = i - 1; j >= 0 && i - j <= step; j--) {
                if (maxCount < counts[j]) {
                    maxCount = counts[j];
                }
            }
            counts[i] += maxCount;
        }
        out.print(counts[n + 1]);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
