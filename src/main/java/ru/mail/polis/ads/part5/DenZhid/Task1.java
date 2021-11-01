package ru.mail.polis.ads.part5.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task1 {

    private static double function(double x) {
        return Math.pow(x, 2.0) + Math.sqrt(x);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double x = 0.0;
        double left = 0.0;
        double right = Math.sqrt(c);
        while (Math.abs(Math.pow(x, 2.0) + Math.sqrt(x) - c) > Math.pow(10.0, -6.0)) {
            x = (left + right) / 2;
            if (function(x) < c) {
                left = x + 1.0;
            } else if (function(x) > c) {
                right = x - 1.0;
            } else {
                break;
            }
        }
        out.println(x);
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
