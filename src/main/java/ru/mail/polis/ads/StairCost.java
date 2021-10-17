package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StairCost {
    // https://www.e-olymp.com/ru/submissions/9559039
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n + 2];
        int[] d = new int[n + 2];
        d[0] = 0;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = in.nextInt();
        }
        d[1] = arr[1];
        int k = in.nextInt();

        for (int i = 1; i <= n + 1; i++) {
            d[i] = arr[i];
            int maxCost = d[i - 1];
            for (int j = i - 2; j >= i - k && j >= 0; j--) {
                if (d[j] > maxCost) {
                    maxCost = d[j];
                }
            }
            d[i] += maxCost;
        }
        out.println(d[n + 1]);
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
