package ru.mail.polis.ads.part4.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int height = in.nextInt();
        int width = in.nextInt();
        int[][] tiles = new int[height][width];
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = in.nextInt();
            }
        }

        int[][] rices = new int[height][width];
        rices[0][0] = tiles[0][0];
        for (int i = 1; i < width; i++) {
            rices[0][i] = rices[0][i - 1] + tiles[0][i];
        }
        for (int i = 1; i < height; i++) {
            rices[i][0] = rices[i - 1][0] + tiles[i][0];
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                rices[i][j] = Math.max(rices[i - 1][j], rices[i][j - 1]) + tiles[i][j];
            }
        }
        StringBuilder str = new StringBuilder();
        int i = height - 1;
        int j = width - 1;
        while (i != 0 || j != 0) {
            if (j == 0) {
                str.append('F');
                i--;
            } else if (i == 0) {
                str.append('R');
                j--;
            } else if (rices[i - 1][j] > rices[i][j - 1]) {
                str.append('F');
                i--;
            } else {
                str.append('R');
                j--;
            }
        }
        out.println(str.reverse());
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
