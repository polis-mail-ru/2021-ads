package ru.mail.polis.ads.part4.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt() + 2;
        int[] c = new int[size];
        c[0] = 0;
        c[size - 1] = 0;
        for (int i = 1; i < size - 1; i++) {
            c[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] d = new int[size];
        d[0] = c[0];
        d[1] = d[0] + c[1];
        for (int i = 2; i < size; i++) {
            int max = Integer.MIN_VALUE;
            int border = Math.max(i - k, 0);
            for (int j = i - 1; j >= border; j--) {
                max = Math.max(max, d[j]);
            }
            d[i] = max + c[i];
        }
        out.println(d[size - 1]);
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
