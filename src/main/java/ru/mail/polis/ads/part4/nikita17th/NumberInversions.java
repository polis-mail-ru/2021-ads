package ru.mail.polis.ads.part4.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class NumberInversions {
    private NumberInversions() {
        // Should not be instantiated
    }

    private static int countInversions(int[] a, int l, int r) {
        if (r - l <= 0) {
            return 0;
        }
        int mid = (l + r) / 2;
        return countInversions(a, l, mid) +
                countInversions(a, mid + 1, r) +
                countSplitInversions(a, l, r, mid);
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(countInversions(a, 0, n - 1));
    }

    private static int countSplitInversions(int[] a, int l, int r, int mid) {
        if (r - l <= 0) {
            return 0;
        }

        int[] buffer = new int[r - l + 1];

        int countInversions = 0;
        int k = 0;
        int lastI = 0;
        int lastJ = 0;
        for (int i = l, j = mid + 1; i <= mid && j <= r; ) {
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
                countInversions += mid - i + 1;
            }
            lastI = i;
            lastJ = j;
        }

        for (int i = lastI; i <= mid; i++) {
            buffer[k++] = a[i];
        }

        for (int i = lastJ; i <= r; i++) {
            buffer[k++] = a[i];
        }

        if (r + 1 - l >= 0) {
            System.arraycopy(buffer, 0, a, l, r + 1 - l);
        }

        return countInversions;
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
