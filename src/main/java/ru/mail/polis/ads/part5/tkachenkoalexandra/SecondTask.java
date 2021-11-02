package part5.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class SecondTask {

    private static final int MIN = 1;
    private static final int MAX = 1_000_000_000;

    private SecondTask() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int width = checkCount(in.nextInt());
        int height = checkCount(in.nextInt());
        int amount = checkCount(in.nextInt());
        long min = Math.max(width, height);
        long max = min * amount;
        long middle;
        while (min < max) {
            middle = (max + min) >>> 1;
            if ((middle / width) * (middle / height) >= amount) {
                max = middle;
            } else {
                min = middle + 1;
            }
        }
        out.println(min);
    }

    private static int checkCount(int n) {
        if ((n < MIN) || (n > MAX)) {
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

