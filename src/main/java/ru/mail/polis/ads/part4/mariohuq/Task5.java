package ru.mail.polis.ads.part4.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 4261. Количество инверсий
 * <p>
 * https://www.e-olymp.com/ru/submissions/9576491
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] input = new int[in.nextInt()];
        Arrays.setAll(input, i -> in.nextInt());
        out.println(countInversions(input));
    }

    private static int countInversions(int[] array) {
        int[] buffer = new int[array.length];
        return countInversions(array, 0, array.length, buffer);
    }

    private static int countInversions(int[] array, int begin, int end, int[] buffer) {
        if (end - begin <= 1) {
            return 0;
        }
        int mid = begin + (end - begin) / 2;
        return countInversions(array, begin, mid, buffer)
                + countInversions(array, mid, end, buffer)
                + countSplitInversions(array, begin, end, buffer);
    }

    private static int countSplitInversions(int[] array, int begin, int end, int[] buffer) {
        int mid = begin + (end - begin) / 2;
        int inversions = 0;
        for (int i = begin, j = mid, k = i; i < mid || j < end; ) {
            if (j >= end || i < mid && array[i] <= array[j]) {
                buffer[k++] = array[i++];
                inversions += j - mid;
            } else {
                buffer[k++] = array[j++];
            }
        }
        if (end - begin >= 0) {
            System.arraycopy(buffer, begin, array, begin, end - begin);
        }
        return inversions;
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
