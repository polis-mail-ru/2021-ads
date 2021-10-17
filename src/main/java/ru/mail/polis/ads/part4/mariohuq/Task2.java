package ru.mail.polis.ads.part4.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 15. Мышки и зернышки
 * <p>
 * https://www.e-olymp.com/ru/submissions/9563169
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Mouse mouse = new Mouse(in);
        out.println(mouse.maxPath());
    }

    static class Mouse {
        public static final char RIGHT = 'R';
        public static final char FORWARD = 'F';

        final int[][] prices;

        public Mouse(int[][] prices) {
            this.prices = prices;
        }

        public Mouse(final FastScanner in) {
            this(readPrices(in));
        }

        public char[] maxPath() {
            final int[][] costs = maxCosts();
            int i = prices.length - 1;
            int j = prices[0].length - 1;
            char[] path = new char[i + j];
            int k = path.length - 1;
            while (i > 0 || j > 0) {
                if (j == 0 || i > 0 && costs[i - 1][j] >= costs[i][j - 1]) {
                    path[k--] = FORWARD;
                    i--;
                } else {
                    path[k--] = RIGHT;
                    j--;
                }
            }
            return path;
        }

        private int[][] maxCosts() {
            final int[][] costs = new int[prices.length][prices[0].length];
            costs[0][0] = prices[0][0];
            for (int i = 1; i < prices.length; i++) {
                costs[i][0] = costs[i - 1][0] + prices[i][0];
            }
            for (int j = 1; j < prices[0].length; j++) {
                costs[0][j] = costs[0][j - 1] + prices[0][j];
            }
            for (int i = 1; i < prices.length; i++) {
                for (int j = 1; j < prices[0].length; j++) {
                    costs[i][j] = Math.max(costs[i - 1][j], costs[i][j - 1]) + prices[i][j];
                }
            }
            return costs;
        }

        private static int[][] readPrices(final FastScanner in) {
            int[][] prices = new int[in.nextInt()][in.nextInt()];
            for (int i = prices.length - 1; i >= 0; i--) {
                for (int j = 0; j < prices[0].length; j++) {
                    prices[i][j] = in.nextInt();
                }
            }
            return prices;
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
