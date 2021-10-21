package ru.mail.polis.ads.part4.pobedos;

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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] buffer = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(countInv(arr, 0, n, buffer));
    }

    private static int countInv(int[] a, int i, int j, int[] buffer) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = (i + j) / 2;
        return countInv(a, i, mid, buffer) + countInv(a, mid, j, buffer) + countSplitInv(a, i, j, mid, buffer);
    }

    private static int countSplitInv(int[] a, int from, int to, int mid, int[] buffer) {
        int i = 0, j = 0;
        int count = 0;

        while (from + i < mid && mid + j < to) {
            if (a[from + i] <= a[mid + j]) {
                buffer[i + j] = a[from + i];
                i++;
            } else {
                buffer[i + j] = a[mid + j];
                j++;
                count += mid - from - i;
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
        return count;
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