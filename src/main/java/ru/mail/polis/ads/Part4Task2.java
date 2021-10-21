package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part4Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] board = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] = in.nextInt();
            }
        }

        int[][] maxSeeds = new int[m][n];
        for (int i = 0; i < n; ++i) {
            for (int j = m - 1; j >= 0; --j) {
                maxSeeds[j][i] = Math.max((j + 1 < m ? maxSeeds[j + 1][i] : 0)
                        + board[j][i], (i - 1 >= 0 ? maxSeeds[j][i - 1] : 0) + board[j][i]);
            }
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while (j >= 0 && i <= m) {
            if (j - 1 >= 0 && i + 1 < m) {
                if (maxSeeds[i][j - 1] >= maxSeeds[i + 1][j]) {
                    builder.append("R");
                    j--;
                } else {
                    builder.append("F");
                    i++;
                }
            } else if (j - 1 >= 0 && !(i + 1 < m)) {
                builder.append("R");
                j--;
            } else if (!(j - 1 >= 0) && i + 1 < m) {
                builder.append("F");
                i++;
            } else {
                builder.append("R");
                j--;
            }
        }
        out.println(builder.reverse().deleteCharAt(builder.lastIndexOf(builder.toString())));
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

