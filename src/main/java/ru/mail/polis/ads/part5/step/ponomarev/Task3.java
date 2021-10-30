package ru.mail.polis.ads.part5.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9691378

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] values = new int[amount];
        int[] sequenceLen = new int[amount];

        for (int i = 0; i < amount; i++) {
            values[i] = in.nextInt();
            sequenceLen[i] = 1;
        }

        int maxSeq = 1;
        for (int i = 0; i < amount; i++) {
            int currValue = values[i];
            for (int j = i - 1; j >= 0; j--) {
                if (values[j] != 0 && currValue % values[j] == 0) {
                    int newSeq = sequenceLen[j] + 1;
                    if (sequenceLen[i] < newSeq) {
                        sequenceLen[i] = newSeq;
                    }
                }
            }
            
            maxSeq = Math.max(maxSeq, sequenceLen[i]);
        }

        out.println(maxSeq);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
