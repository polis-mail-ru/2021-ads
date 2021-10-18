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
public final class Part4Task1 {
    private Part4Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = "";
        try {
            input = in.reader.readLine();
        } catch (IOException e) {
            return;
        }
        if (input.isEmpty()) {
            return;
        }
        int[][] d = new int[input.length()][input.length()];
        for (int i = 0; i < input.length(); i++) {
            d[i][i] = 1;
        }
        for (int i = input.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < input.length(); j++) {
                int tmpSum = Integer.MAX_VALUE;
                if (input.charAt(i) == '(' && input.charAt(j) == ')' || input.charAt(i) == '[' && input.charAt(j) == ']') {
                    tmpSum = d[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    tmpSum = Math.min(d[i][k] + d[k + 1][j], tmpSum);
                }
                d[i][j] = tmpSum;
            }
        }
        out.println(rightSubsequence(input, d, 0, input.length() - 1));
    }

    private static String rightSubsequence(String s, int[][] d, int i, int j) {
        if (j < i) {
            return "";
        }
        if (j == i) {
            return s.charAt(i) == '(' || s.charAt(i) == ')' ? "()" : "[]";
        }
        if ((s.charAt(i) == '(' && s.charAt(j) == ')' || s.charAt(i) == '[' && s.charAt(j) == ']')
                && d[i + 1][j - 1] == d[i][j]) {
            return s.charAt(i) + rightSubsequence(s, d, i + 1, j - 1) + s.charAt(j);
        }
        for (int k = i; k < j; k++) {
            if (d[i][k] + d[k + 1][j] == d[i][j]) {
                return rightSubsequence(s, d, i, k) + rightSubsequence(s, d, k + 1, j);
            }
        }
        return "";
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
