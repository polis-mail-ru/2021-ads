package ru.mail.polis.ads.ponomarev.stepan.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2 {
    final static int DIFFERENCE = 107;
    final static int MAX_SIZE = DIFFERENCE * 2;
    final static int[] VALUE_REPEAT_COUNT = new int[MAX_SIZE];

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int amount = in.nextInt();
        final int minValue = in.nextInt() - DIFFERENCE;
        VALUE_REPEAT_COUNT[getIndex(minValue, minValue + DIFFERENCE)]++;

        for (int i = 0; i < amount - 1; i++) {
            VALUE_REPEAT_COUNT[getIndex(minValue, in.nextInt())]++;
        }

        for (int i = 0; i < MAX_SIZE; i++) {
            if (VALUE_REPEAT_COUNT[i] <= 0) {
                continue;
            }

            int currentValue = i + minValue;
            for (int j = 0; j < VALUE_REPEAT_COUNT[i]; j++) {
                out.print(currentValue + " ");
            }
        }
    }

    private static int getIndex(int minValue, int currentValue) {
        return currentValue - minValue;
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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