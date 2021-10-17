package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MouseAndCorn {
    //https://www.e-olymp.com/ru/submissions/9558012
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[m][n];
        int[][] maxCorns = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int sumDown = 0;
        int sumLeft = 0;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; j++) {
                sumDown = a[i][j] + (i + 1 < m ? maxCorns[i + 1][j] : 0);
                sumLeft = a[i][j] + (j - 1 >= 0 ? maxCorns[i][j - 1] : 0);
                maxCorns[i][j] = Math.max(sumDown, sumLeft);
            }
        }
        StringBuilder reversePath = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while ((i != m - 1) || j != 0) {
            if (i == m - 1) {
                reversePath.append("R");
                j--;
                continue;
            }
            if (j == 0) {
                reversePath.append("F");
                i++;
                continue;
            }
            if (maxCorns[i + 1][j] >= maxCorns[i][j - 1]) {
                reversePath.append("F");
                i++;
            } else {
                reversePath.append("R");
                j--;
            }
        }
        out.println(reversePath.reverse());

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
