package ru.mail.polis.ads.part4.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class MouseAndGrains {
    private MouseAndGrains() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] sumOfPath = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sumOfPath[i][j] = in.nextInt();
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    if (j - 1 >= 0 && sumOfPath[i][j - 1] > sumOfPath[i + 1][j]) {
                        sumOfPath[i][j] = sumOfPath[i][j - 1] + sumOfPath[i][j];
                    } else {
                        sumOfPath[i][j] = sumOfPath[i + 1][j] + sumOfPath[i][j];
                    }
                } else if (j - 1 >= 0) {
                    sumOfPath[i][j] = sumOfPath[i][j - 1] + sumOfPath[i][j];
                }
            }
        }

        StringBuilder revPath = new StringBuilder();

        int i = 0;
        int j = m - 1;

        while (i != n - 1 || j != 0) {
            if (i < n - 1 && j >= 1 && sumOfPath[i + 1][j] > sumOfPath[i][j - 1]) {
                i++;
                revPath.append('F');
            } else if (i < n - 1 && j >= 1 && sumOfPath[i + 1][j] < sumOfPath[i][j - 1]) {
                j--;
                revPath.append('R');
            } else if (i < n - 1) {
                i++;
                revPath.append('F');
            } else if (j >= 1) {
                j--;
                revPath.append('R');
            }

        }

        out.println(revPath.reverse());
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
