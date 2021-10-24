package ru.mail.polis.ads.part5.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9639772
public final class Week5Test5 {
    private Week5Test5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }

        do {
            for (int i = 0; i < n; ++i) {
                out.print(array[i] + " ");
            }
            out.println();
        } while (sortNext(array, n));
    }

    private static final void swap(final int[] array, final int pos1, final int pos2) {
        if (array[pos1] == array[pos2]) {
            return;
        }
        array[pos1] ^= array[pos2];
        array[pos2] ^= array[pos1];
        array[pos1] ^= array[pos2];

    }

    private static boolean sortNext(final int[] a, final int n) {
        int i = n - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            --i;
        }
        if (i < 0) {
            return false;
        }
        int k = n - 1;
        while (a[i] >= a[k]) {
            --k;
        }
        swap(a, i, k);
        int l = i + 1, r = n - 1;
        while (l < r) {
            swap(a, l++, r--);
        }
        return true;
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
