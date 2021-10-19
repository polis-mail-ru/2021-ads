package ru.mail.polis.ads.part4.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class /**/LongestSubsequence {
    private LongestSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int[] firstSequence = new int[n];

        for (int i = 1; i < n; i++) {
            firstSequence[i] = in.nextInt();
        }

        int m = in.nextInt() + 1;
        int[] secondSequence = new int[m];
        for (int i = 1; i < m; i++) {
            secondSequence[i] = in.nextInt();
        }

        int[][] d = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (firstSequence[i] == secondSequence[j]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        out.println(d[n - 1][m - 1]);
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
