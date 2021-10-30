package ru.mail.polis.ads.part5.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part5Task5 {
    private Part5Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        out.println(arrayToString(a));
        int k = 1;
        for (int i = 1; i <= n; i++) {
            k *= i;
        }
        for (int i = 0; i < k - 1; i++) {
            int p1 = n - 1;
            while (a[p1 - 1] > a[p1]) {
                p1--;
            }
            int p2 = n - 1;
            while (a[p2] < a[p1 - 1]) {
                p2--;
            }
            int tmp = a[p2];
            a[p2] = a[p1 - 1];
            a[p1 - 1] = tmp;

            for (int j = p1; j < p1 + (n - p1) / 2; j++) {
                tmp = a[j];
                a[j] = a[n - 1 - (j - p1)];
                a[n - 1 - (j - p1)] = tmp;
            }
            out.println(arrayToString(a));
        }
    }

    public static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length - 1; i++) {
            sb.append(a[i]).append(" ");
        }
        sb.append(a[a.length - 1]);
        return sb.toString();
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
