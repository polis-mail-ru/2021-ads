package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// TODO: Как решить эту жесть кто это придумал а?А/А/А/А/

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        final int[] seq1 = new int[size];
        for (int i = 0; i < size; i++) {
            seq1[i] = in.nextInt();
        }

        size = in.nextInt();
        final int[] seq2 = new int[size];
        for (int i = 0; i < size; i++) {
            seq2[i] = in.nextInt();
        }
        
        
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
