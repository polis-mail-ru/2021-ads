package ru.mail.polis.ads.part4.vspochernin.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9539859
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] c = new int[n + 2]; // Стоимость каждой ступеньки.
        int[] d = new int[n + 2]; // Максимальная стоимость пути для каждой ступеньки.
        for (int i = 0; i < n; i++) {
            c[i + 1] = in.nextInt();
        }
        int k = in.nextInt();

        d[0] = 0; // База.
        // d(i) = max(d(i - 1), d(i - 2), ..., d(0)) + c(i).
        int maxD = 0;
        for (int i = 1; i < k; i++) {
            d[i] = maxD + c[i];
            maxD = Math.max(maxD, d[i]);
        }
        // d(i) = max(d(i - 1), d(i - 2), ..., d(i - k)) + c(i).
        for (int i = k; i < n + 2; i++) {
            d[i] = maxD + c[i];
            maxD = d[i];
            for (int j = 1; j < k; j++) {
                maxD = Math.max(maxD, d[i - j]);
            }
        }
        System.out.println(d[n + 1]);
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
