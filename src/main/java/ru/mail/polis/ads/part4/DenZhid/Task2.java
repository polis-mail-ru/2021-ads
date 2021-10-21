package ru.mail.polis.ads.part4.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] floor = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floor[i][j] = in.nextInt();
            }
        }
        int[][] seedsMatrix = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            seedsMatrix[i][0] = 0;
        }
        for (int j = 0; j < n + 1; j++) {
            seedsMatrix[m][j] = 0;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n + 1; j++) {
                seedsMatrix[i][j] = Math.max(seedsMatrix[i + 1][j], seedsMatrix[i][j - 1]) + floor[i][j - 1];
            }
        }
        int i = 0;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while (i != m - 1 || j != 1) {
            if (i == m - 1) {
               sb.append("R");
               j--;
            } else if (j == 1) {
                sb.append("F");
                i++;
            } else if (seedsMatrix[i][j - 1] == seedsMatrix[i][j] - floor[i][j - 1]) {
                sb.append("R");
                j--;
            } else if (seedsMatrix[i + 1][j] == seedsMatrix[i][j] - floor[i][j - 1]) {
                sb.append("F");
                i++;
            }
        }
        out.println(sb.reverse());
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
