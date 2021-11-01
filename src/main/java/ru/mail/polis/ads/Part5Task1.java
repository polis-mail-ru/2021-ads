package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part5Task1 {

    private static final double EPSILON = 1E-6;

    private static void solve(final FastScanner in, final PrintWriter out) {
        double C = Double.parseDouble(in.next());
        double left = 0;
        double right = C;
        double mid = (left + right) / 2;
        while (Math.abs(getFunctionRes(mid, C)) > EPSILON) {
            if (getFunctionRes(mid, C) < 0) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (right + left) / 2;
        }
        out.println(mid);
    }

    private static double getFunctionRes(double x, double C) {
        return x * x + Math.sqrt(x) - C;
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

