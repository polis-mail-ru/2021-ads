package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MouseBeReachTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] data = new int[m + 1][n + 1];
        for (int row = 0; row < m; row++) {
            for (int column = 1; column <= n; column++) {
                //[m-row]for convenience
                data[m - row][column] = in.nextInt();
            }
        }
        int[][] dynamicMagic = new int[m + 1][n + 1];
        boolean[][] fromBack = new boolean[m][n];//true = F
        for (int row = 1; row <= m; row++) {
            for (int column = 1; column <= n; column++) {
                dynamicMagic[row][column] = data[row][column];
                if (dynamicMagic[row - 1][column] > dynamicMagic[row][column - 1]) {
                    dynamicMagic[row][column] += dynamicMagic[row - 1][column];
                    fromBack[row - 1][column - 1] = true;
                    continue;
                }
                dynamicMagic[row][column] += dynamicMagic[row][column - 1];
            }
        }
        StringBuilder answer = new StringBuilder();
        int i = m - 1;
        int j = n - 1;
        while (i != 0 || j != 0) {
            if (i != 0 && j != 0) {
                if (fromBack[i][j]) {
                    i--;
                    answer.append('F');
                } else {
                    j--;
                    answer.append('R');
                }
                continue;
            }
            if (i != 0) {
                i--;
                answer.append('F');
                continue;
            }
            j--;
            answer.append('R');
        }
        out.println(answer.reverse());
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