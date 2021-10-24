package ru.mail.polis.ads.part5.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9639443
public final class Week5Test3 {
    private Week5Test3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] input = new int[in.nextInt()];
        for (int i = 0; i < input.length; ++i) {
            input[i] = in.nextInt();
        }
        
        int[] d = new int[input.length + 1];
        int max = 1;
        for (int i = 0; i < input.length; ++i) {
            d[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (input[j] != 0 && input[i] % input[j] == 0) {
                    d[i] = Math.max(d[i], d[j] + 1);
                    max = Math.max(max, d[i]);
                } 
            }
        }
        out.println(max);
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
