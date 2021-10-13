package ru.mail.polis.ads.part4.vspochernin.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9531835
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int countInv = 0;

    public static void merge(int[] a, int from, int mid, int to, int[] buffer) {
        int i = 0;
        int j = 0;

        while (from + i < mid && mid + j < to) {
            if (a[from + i] <= a[mid + j]) {
                buffer[i + j] = a[from + i];
                i++;
            } else {
                buffer[i + j] = a[mid + j];
                j++;
                countInv += mid - (from + i);
            }
        }
        while (from + i < mid) {
            buffer[i + j] = a[from + i];
            i++;
        }
        while (mid + j < to) {
            buffer[i + j] = a[mid + j];
            j++;
        }

        for (i = 0; i < to - from; i++) {
            a[from + i] = buffer[i];
        }
    }

    private static void mergeSort(int[] a, int from, int to, int[] buffer) {
        if (from == to - 1) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        mergeSort(a, from, mid, buffer);
        mergeSort(a, mid, to, buffer);
        merge(a, from, mid, to, buffer);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        countInv = 0;
        int[] buffer = new int[n];
        mergeSort(a, 0, n, buffer);
        out.println(countInv);
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
