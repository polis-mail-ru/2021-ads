package ru.mail.polis.ads.part4.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 1087. Скобочная последовательность
 * <p>
 * https://www.e-olymp.com/ru/submissions/9575649
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.nextOrNull();
        out.println((input != null) ? minSuperSequence(input.toCharArray()) : "");
    }

    private static final String OPENING_BRACKETS = "([";
    private static final String CLOSING_BRACKETS = ")]";

    static String minSuperSequence(char[] sequence) {
        final StringBuilder sb = new StringBuilder();
        final int[][] costs = new int[sequence.length][sequence.length];
        final int[][] minSplits = new int[sequence.length][sequence.length];
        for (int i = 0; i < minSplits.length; i++) {
            for (int j = 0; j < minSplits[0].length; j++) {
                minSplits[i][j] = -1;
            }
        }
        for (int i = 0; i < costs.length; i++) {
            costs[i][i] = 1;
        }
        for (int step = 1; step < costs.length; step++) {
            for (int i = 0; i < costs.length - step; i++) {
                minSplits[i][i + step] = minSplit(costs, i, i + step);
                final int minSplitSum = splitSum(costs, i, minSplits[i][i + step], i + step);
                costs[i][i + step] = isPair(sequence[i], sequence[i + step])
                        ? Math.min(costs[i + 1][i + step - 1], minSplitSum)
                        : minSplitSum;
            }
        }
        append(0, sequence.length - 1, sb, sequence, costs, minSplits);
        return sb.toString();
    }

    private static void append(int i, int j, StringBuilder sb, char[] sequence, int[][] costs, int[][] minSplits) {
        if (i > j) {
            return;
        }
        if (i == j) {
            int opening = OPENING_BRACKETS.indexOf(sequence[i]);
            int type = opening != -1 ? opening : CLOSING_BRACKETS.indexOf(sequence[i]);
            sb.append(OPENING_BRACKETS.charAt(type));
            sb.append(CLOSING_BRACKETS.charAt(type));
            return;
        }
        if (isPair(sequence[i], sequence[j]) && costs[i][j] == costs[i + 1][j - 1]) {
            sb.append(sequence[i]);
            append(i + 1, j - 1, sb, sequence, costs, minSplits);
            sb.append(sequence[j]);
            return;
        }
        append(i, minSplits[i][j], sb, sequence, costs, minSplits);
        append(minSplits[i][j] + 1, j, sb, sequence, costs, minSplits);
    }

    private static int minSplit(int[][] costs, int i, int j) {
        int minSplit = i;
        for (int k = i + 1; k < j; k++) {
            if (splitSum(costs, i, k, j) < splitSum(costs, i, minSplit, j)) {
                minSplit = k;
            }
        }
        return minSplit;
    }

    private static int splitSum(int[][] costs, int i, int mid, int j) {
        return costs[i][mid] + costs[mid + 1][j];
    }

    private static boolean isPair(char left, char right) {
        return OPENING_BRACKETS.indexOf(left) != -1
                && OPENING_BRACKETS.indexOf(left) == CLOSING_BRACKETS.indexOf(right);
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

        /**
         * Same as next(), except it returns null if end of the input is reached
         */
        String nextOrNull() {
            try {
                return next();
            } catch (NullPointerException e) {
                // end of input
                return null;
            }
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
