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
public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = in.nextInt();
        }
        int[] d = new int[size];
        d[0] = 1;
        int resultMax = Integer.MIN_VALUE;
        for (int i = 1; i < size; i++) {
            int max = Integer.MIN_VALUE;
            for (int k = i - 1; k >= 0; k--) {
                if (d[k] > max && a[k] != 0 && a[i] % a[k] == 0) {
                    max = d[k];
                }
            }
            if (max != Integer.MIN_VALUE) {
                d[i] = max + 1;
            } else {
                d[i] = 1;
            }
            if (d[i] > resultMax) {
                resultMax = d[i];
            }
        }
        out.println(resultMax);
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
