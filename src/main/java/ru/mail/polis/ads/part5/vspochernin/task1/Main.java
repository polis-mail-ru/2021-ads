package ru.mail.polis.ads.part5.vspochernin.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9607861
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());
        double min = 0;
        double max = c;
        double x = (min + max) / 2;
        double value = x * x + Math.sqrt(x);
        while (Math.abs(value - c) >= 0.000001) {
            if (value > c) {
                max = x;
            } else {
                min = x;
            }
            x = (min + max) / 2;
            value = x * x + Math.sqrt(x);
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
