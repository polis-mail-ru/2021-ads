package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9561626

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] firstSequence = new int[size];

        for (int i = 0; i < size; i++) {
            firstSequence[i] = in.nextInt();
        }

        size = in.nextInt();
        int[] secondSequence = new int[size];

        for (int i = 0; i < size; i++) {
            secondSequence[i] = in.nextInt();
        }

        int[][] story = new int[firstSequence.length][secondSequence.length];
        for (int i = 0; i < firstSequence.length; i++) {
            for (int j = 0; j < secondSequence.length; j++) {
                if (i > 0) {
                    story[i][j] = story[i - 1][j];
                }

                if (j > 0 && story[i][j] < story[i][j - 1]) {
                    story[i][j] = story[i][j - 1];
                }

                if (firstSequence[i] == secondSequence[j]) {
                    story[i][j]++;
                }
            }
        }

        out.println(story[firstSequence.length - 1][secondSequence.length - 1]);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
