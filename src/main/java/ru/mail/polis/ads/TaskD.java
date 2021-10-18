package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] sequence = new int[n+2];
        for (int i = 1; i <= n; i++) {
            sequence[i] = in.nextInt();
        }
        int k = in.nextInt();
        for (int i = 1; i < n + 2; i++) {
            int maxValFromPrev = sequence[i-1];
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0 && sequence[i - j] > maxValFromPrev) {
                    maxValFromPrev = sequence[i - j];
                }
            }
            sequence[i] = maxValFromPrev + sequence[i];
        }
        System.out.println(sequence[n+1]);
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
