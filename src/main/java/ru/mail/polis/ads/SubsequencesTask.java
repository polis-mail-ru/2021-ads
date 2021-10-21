package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SubsequencesTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        int[] sequence1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            sequence1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] sequence2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            sequence2[i] = in.nextInt();
        }

        int[][] dynamicMagic = new int[n2 + 1][n1 + 1];

        for (int column = 0; column <= n1; column++) {
            for (int row = 0; row <= n2; row++) {
                if (column == 0 || row == 0) {
                    continue;
                }
                if (sequence1[column - 1] == sequence2[row - 1]) {
                    dynamicMagic[row][column] = dynamicMagic[row - 1][column - 1] + 1;
                    continue;
                }
                dynamicMagic[row][column] = Math.max(dynamicMagic[row - 1][column], dynamicMagic[row][column - 1]);
            }
        }
        out.println(dynamicMagic[n2][n1]);
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