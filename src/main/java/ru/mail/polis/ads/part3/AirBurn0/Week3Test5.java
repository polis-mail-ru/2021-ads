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
public final class Week3Test5 {
    private Week3Test5() {
        // Should not be instantiated
    }
    
    // https://www.e-olymp.com/ru/submissions/9490949
    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];

        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
        }

        heapSort(array);

        out.print(array[0]);
        for (int i = 1; i < array.length; ++i) {
            out.print(" " + array[i]);
        }
    }

    static final void swap(final int[] array, final int pos1, final int pos2) {
        if (array[pos1] == array[pos2]) {
            return;
        }
        array[pos1] ^= array[pos2];
        array[pos2] ^= array[pos1];
        array[pos1] ^= array[pos2];

    }

    static final void sink(final int[] array, int pos, final int length) {
        int j;
        while ((j = 2 * pos + 1) <= length) {
            if (j < length && array[j] < array[j + 1]) {
                ++j;
            }
            if (array[pos] >= array[j]) {
                break;
            }
            swap(array, pos, j);
            pos = j;
        }
    }

    static final void heapSort(final int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 0; --k) {
            sink(array, k, n);
        }

        while (n > 0) {
            swap(array, 0, n--);
            sink(array, 0, n);
        }

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
