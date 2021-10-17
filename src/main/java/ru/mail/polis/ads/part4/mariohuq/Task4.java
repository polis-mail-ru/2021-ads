package ru.mail.polis.ads.part4.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 262. Лесенка
 * <p>
 * https://www.e-olymp.com/ru/submissions/9564914
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] prices = new int[in.nextInt() + 2];
        for (int i = 1; i < prices.length - 1; i++) {
            prices[i] = in.nextInt();
        }
        // prices[0] == 0 && prices[prices.length - 1] == 0
        out.println(maxCost(prices, in.nextInt()));
    }

    private static int maxCost(int[] prices, int k) {
        int[] costs = new int[prices.length];
        costs[0] = prices[0];
        for (int i = 1; i < k; i++) {
            costs[i] = max(costs, 0, i) + prices[i];
        }
        for (int i = k; i < prices.length; i++) {
            costs[i] = max(costs, i - k, i) + prices[i];
        }
        return costs[costs.length - 1];
    }

    private static int max(int[] array, int begin, int end) {
        int result = array[begin];
        for (int i = begin + 1; i < end; i++) {
            result = Math.max(result, array[i]);
        }
        return result;
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
