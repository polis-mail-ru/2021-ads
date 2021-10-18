package ru.mail.polis.ads.part4.vspochernin.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = new Scanner(System.in).nextLine();
        if (s == null || s.length() == 0) {
            out.println("");
            return;
        }
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

        // Восстановление ответа
        if (d[n - 1][n - 1] == 0) {
            out.println(s);
        } else {
            out.println(generateString(s, d, 0, n - 1));
        }
    }

    private static String generateString(String s, int[][] d, int i, int j) {
        if (i > j) {
            return "";
        } else if (i == j) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                return "()";
            } else if (s.charAt(i) == '[' || s.charAt(i) == ']') {
                return "[]";
            }
        } else {
            if (s.charAt(i) == '(' && s.charAt(j) == ')') {
                return "(" + generateString(s, d, i + 1, j - 1) + ")";
            } else if (s.charAt(i) == '[' && s.charAt(j) == ']') {
                return "[" + generateString(s, d, i + 1, j - 1) + "]";
            } else {
                for (int k = i; k < j; k++) {
                    if (d[i][k] + d[k + 1][j] == d[i][j]) {
                        return generateString(s, d, i, k) + generateString(s, d, k + 1, j);
                    }
                }
            }
        }
        return null;
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
