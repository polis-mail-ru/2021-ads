package ru.mail.polis.ads.part4.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] costs = new int[n + 2];
        int[] d = new int[n + 2];
        d[0] = 0;
        costs[1] = in.nextInt();
        d[1] = costs[1];
        costs[n + 1] = 0;
        for (int i = 2; i < n + 1; i++) {
            costs[i] = in.nextInt();
        }
        int step = in.nextInt();
        for (int i = 2; i < n + 2; i++) {
            d[i] = getMax(d, i - 1, step) + costs[i];
        }
        out.println(d[n + 1]);
    }

    private static int getMax(int[] arr, int index, int step) {
        int max = arr[index];
        for (int i = index - 1; (i >= 0) && (index - i < step); i--) {
            max = Math.max(max, arr[i]);
        }
        return max;
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
