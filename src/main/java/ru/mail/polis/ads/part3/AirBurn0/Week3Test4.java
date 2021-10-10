package ru.mail.polis.ads.part3.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Test4 {
    private Week3Test4() {
        // Should not be instantiated
    }
    
    // https://www.e-olymp.com/ru/submissions/9484149
    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];

        int child;
        int parent;
        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
            child = i;
            while (child > 0 && array[child] > array[parent = (child - 1) >> 1]) {
                swap(array, child, parent);
                child = parent;
            }
        }

        out.print(array[0]);
        for (int i = 1; i < array.length; ++i) {
            out.print(" " + array[i]);
        }
    }

    static final void swap(final int[] array, final int pos1, final int pos2) {
        array[pos1] ^= array[pos2];
        array[pos2] ^= array[pos1];
        array[pos1] ^= array[pos2];
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
