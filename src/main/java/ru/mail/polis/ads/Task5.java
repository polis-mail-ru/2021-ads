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
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void print(int a[], final PrintWriter out) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int el : a) {
            stringBuilder.append(el).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        out.println(stringBuilder);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        print(a, out);
        while (nextSeq(a)) {
            print(a, out);
        }
    }

    private static boolean nextSeq(int[] a) {
        int j = a.length - 2;
        while ((j != -1) && (a[j] >= a[j + 1])) {
            j--;
        }
        if (j == -1) {
            return false;
        }
        int k = a.length - 1;
        while (a[j] >= a[k]) {
            k--;
        }
        swap(a, j, k);
        int l = j + 1;
        int r = a.length - 1;
        while (l < r) {
            swap(a, l++, r--);
        }
        return true;
    }

    private static void swap(int[] a, int j, int k) {
        int temp = a[k];
        a[k] = a[j];
        a[j] = temp;
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
