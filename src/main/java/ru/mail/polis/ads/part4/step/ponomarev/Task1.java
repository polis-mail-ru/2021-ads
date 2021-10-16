package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1 {
    private static final int PARTITION_STUB = -1;
    private static int[][] costTable;
    private static int[][] partitionKeeper;

    private static void solve(final FastScanner in, final PrintWriter out) throws Exception {
        String sequence = in.next();
        int len = sequence.length();
        costTable = new int[len][len];
        partitionKeeper = new int[len][len];

        for (int i = 0; i < len; i++) {
            costTable[i][i] = 1;
        }

        for (int end = 0; end < len; end++) {
            for (int start = end - 1; start >= 0; start--) {
                final char openedBracket = sequence.charAt(start);
                final char closedBracket = sequence.charAt(end);

                int currentChunkCost = Integer.MAX_VALUE;
                partitionKeeper[start][end] = start;

                if (isPair(openedBracket, closedBracket)) {
                    currentChunkCost = costTable[start + 1][end - 1];
                    partitionKeeper[start][end] = PARTITION_STUB;
                }

                costTable[start][end] = getChunkCost(start, end, currentChunkCost);
            }
        }

        String restoredSequence = restoreSequence(sequence, 0, costTable.length - 1);
        out.println(restoredSequence);
    }

    private static int getChunkCost(int start, int end, int defaultValue) {
        int currentChunkCost = defaultValue;

        for (int k = start; k < end; k++) {
            int newMin = costTable[start][k] + costTable[k + 1][end];

            if (newMin < currentChunkCost) {
                currentChunkCost = newMin;
                partitionKeeper[start][end] = k;
            }
        }

        return currentChunkCost;
    }

    private static String restoreSequence(String sequence, int start, int end) {
        if (start == end) {
            return restore(sequence.charAt(start));
        }

        if (partitionKeeper[start][end] == PARTITION_STUB) {
            return sequence.charAt(start)
                    + restoreSequence(sequence, start + 1, end - 1)
                    + sequence.charAt(end);
        }

        if (costTable[start][end] == 0) {
            return sequence.substring(start, end + 1);
        }

        return restoreSequence(sequence, start, partitionKeeper[start][end])
                + restoreSequence(sequence, partitionKeeper[start][end] + 1, end);
    }

    private static boolean isPair(final char open, final char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']');
    }

    private static String restore(final char open) {
        return open == '(' || open == ')'
                ? "()"
                : "[]";
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
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
}