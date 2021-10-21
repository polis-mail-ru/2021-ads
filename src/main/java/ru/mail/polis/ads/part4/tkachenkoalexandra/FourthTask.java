package tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class FourthTask {

    private static final int MAX = 1000;

    private FourthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] cost = new int[checkCount(in.nextInt()) + 2];
        IntStream.range(1, cost.length - 1).forEach(i -> cost[i] = in.nextInt());
        int stepLimit = checkCount(in.nextInt());
        int[] d = new int[cost.length];

        for (int i = 1; i < d.length; i++) {
            d[i] = max(Arrays.copyOfRange(d, i < stepLimit ? 0 : i - stepLimit, i)) + cost[i];
        }
        out.println(d[d.length - 1]);
    }

    private static int max(int[] array) {
        Arrays.sort(array);
        return array[array.length - 1];
    }


    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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
