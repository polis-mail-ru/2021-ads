package ru.mail.polis.ads.part4.vspochernin.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9563238
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = a[i][j];
                } else if (i == 0) {
                    d[i][j] = d[i][j - 1] + a[i][j];
                } else if (j == 0) {
                    d[i][j] = d[i - 1][j] + a[i][j];
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]) + a[i][j];
                }
            }
        }

        // Восстанавливаем ответ.
        char[] answer = new char[m + n - 2];
        int count = 0;
        int i = m - 1;
        int j = n - 1;
        while (i != 0 || j != 0) {
            if (i == 0) {
                answer[count++] = 'R';
                j--;
            } else if (j == 0) {
                answer[count++] = 'F';
                i--;
            } else {
                if (d[i - 1][j] > d[i][j - 1]) {
                    answer[count++] = 'F';
                    i--;
                } else {
                    answer[count++] = 'R';
                    j--;
                }
            }
        }
        for (i = m + n - 3; i >= 0; i--) {
            out.print(answer[i]);
        }
        out.println();
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
