package ru.mail.polis.ads;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static double func(double x) {
        return x * x + Math.sqrt(x);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double begin = 1.0;
        double end = c;
        double middle = (begin + end) / 2;
        double midFunc = func(middle);
        while (Math.abs(c - midFunc) > 0.0000001) {
            if (c < midFunc) {
                end = middle;
            }
            if (midFunc < c) {
                begin = middle;
            }
            middle = (begin + end) / 2;
            midFunc = func(middle);
        }
        DecimalFormat df = new DecimalFormat("0.000000");
        df.setRoundingMode(RoundingMode.FLOOR);
        out.println(df.format(middle));
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
