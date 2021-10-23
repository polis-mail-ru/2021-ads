package ru.mail.polis.ads.part5.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class Sqrt {
    private Sqrt() {
        // Should not be instantiated
    }

    private static final double EPS = 1e-7;

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double left = 0;
        double right = c;
        double median;
        double functionValue;
        while (Math.abs(left - right) > EPS) {
            median = (left + right) / 2;
            functionValue = Math.pow(median, 2) + Math.sqrt(median);
            if (functionValue == c) {
                left = median;
                break;
            }
            if (functionValue > c) {
                right = median;
            } else {
                left = median;
            }
        }

        out.println(left);
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

        double nextDouble() {
            return Double.parseDouble(next());
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
