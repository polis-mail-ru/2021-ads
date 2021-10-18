package ru.mail.polis.ads.part4.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 1618. Наибольшая общая подпоследовательность
 * <p>
 * https://www.e-olymp.com/ru/submissions/9577387
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] first = new int[in.nextInt()];
        for (int i = 0; i < first.length; i++)
            first[i] = in.nextInt();
        int[] second = new int[in.nextInt()];
        for (int i = 0; i < second.length; i++)
            second[i] = in.nextInt();
        out.println(maxCommonSubSequenceLength(first, second));
    }

    static int maxCommonSubSequenceLength(int[] first, int[] second) {
        int[][] answers = new int[first.length][second.length];
        answers[0][0] = first[0] != second[0] ? 0 : 1;
        for (int i = 1; i < first.length; i++) {
            answers[i][0] = Math.max(first[i] != second[0] ? 0 : 1, answers[i - 1][0]);
        }
        for (int j = 1; j < second.length; j++) {
            answers[0][j] = Math.max(first[0] != second[j] ? 0 : 1, answers[0][j - 1]);
        }
        for (int i = 1; i < first.length; i++) {
            for (int j = 1; j < second.length; j++) {
                answers[i][j] = Math.max(first[i] != second[j] ? 0 : answers[i - 1][j - 1] + 1, Math.max(answers[i - 1][j], answers[i][j - 1]));
            }
        }
        return answers[first.length - 1][second.length - 1];
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
