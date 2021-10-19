package ru.mail.polis.ads.part4.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Ladder {
    private Ladder() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 2;
        int[] ladder = new int[n];
        for (int i = 1; i < n - 1; i++) {
            ladder[i] = in.nextInt();
        }

        int k = in.nextInt();
        int[] sum = new int[n];

        int max;
        for (int i = 1; i < n; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 1; j <= k && i - j >= 0; j++) {
                max = Integer.max(ladder[i] + sum[i - j], max);
            }
            sum[i] = max;
        }
        out.println(sum[n - 1]);
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
