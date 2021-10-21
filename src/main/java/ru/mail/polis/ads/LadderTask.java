package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LadderTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            data[i] = in.nextInt();
        }
        int k = in.nextInt();

        int[] score = new int[n + 2];

        for (int i = 1; i < n + 2; i++) {
            score[i] = data[i];
            int behind = Math.max(i - k, 0);
            int maxBehind = score[i - 1];
            for (int j = behind; j < i; j++) {
                if (score[j] > maxBehind) {
                    maxBehind = score[j];
                }
            }
            score[i] += maxBehind;
        }
        out.println(score[n + 1]);
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
