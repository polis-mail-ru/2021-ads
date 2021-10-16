package part3.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class FirstTask {

    private static final int MAX = 100000;

    private FirstTask() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[checkCount(in.nextInt())];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
            if (array[i] < array[(i - 1) / 2]) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
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

