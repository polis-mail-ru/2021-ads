package ru.mail.polis.ads.part4.vspochernin.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
        int n = s.length();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
        }

        // Заполнение матрицы.
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if ((s.charAt(i) == '(' && s.charAt(j) == ')') || (s.charAt(i) == '[' && s.charAt(j) == ']')) {
                    d[i][j] = d[i + 1][j - 1];
                } else {
                    int min = d[i][i] + d[i + 1][j];
                    for (int k = i + 1; k < j; k++) {
                        min = Math.min(min, d[i][k] + d[k + 1][j]);
                    }
                    d[i][j] = min;
                }
            }
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
