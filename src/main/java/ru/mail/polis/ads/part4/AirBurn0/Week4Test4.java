package ru.mail.polis.ads.part4.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9565949
public final class Week4Test4 {
    private Week4Test4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] input = new int[in.nextInt() + 2];
        input[0] = 0;
        for (int i = 1; i < input.length - 1; ++i) {
            input[i] = in.nextInt();
        }
        input[input.length - 1] = 0;
        int step = in.nextInt();
        int tempMax;
        for (int i = 2; i < input.length; ++i) {
            tempMax = input[i - 1];
            for (int j = 2; j <= step; ++j) {
                if (i >= j) {
                    tempMax = Math.max(tempMax, input[i - j]);
                }
            }
            input[i] = tempMax + input[i];
        }

        out.println(input[input.length - 1]);
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
