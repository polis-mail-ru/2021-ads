package ru.mail.polis.ads.part4.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part4Task4 {
    private Part4Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairsCost = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            stairsCost[i] = in.nextInt();
        }
        int step = in.nextInt();
        for (int i = 1; i <= n + 1; i++) {
            int tmpMax = stairsCost[i - 1];
            for (int j = 1; j <= step && i - j >= 0; j++) {
                tmpMax = Math.max(tmpMax, stairsCost[i - j]);
            }
            stairsCost[i] = tmpMax + stairsCost[i];
        }
        out.println(stairsCost[n + 1]);
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
