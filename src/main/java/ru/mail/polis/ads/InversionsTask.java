package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class InversionsTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        out.println(countInversions(data));
    }

    private static int countInversions(int[] array) {
        int[] temp = new int[array.length];
        return mergeSort(array, temp, 0, array.length);
    }

    private static int mergeSort(int[] array, int[] temp, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return 0;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        return mergeSort(array, temp, fromInclusive, mid) +
                mergeSort(array, temp, mid, toExclusive) +
                merge(array, temp, fromInclusive, mid, toExclusive);
    }

    private static int merge(int[] array, int[] temp, int fromInclusive, int mid, int toExclusive) {
        int i = 0;
        int i1 = fromInclusive;
        int i2 = mid;
        int inversions = 0;
        while (i1 < mid || i2 < toExclusive) {
            if (i1 < mid && (i2 == toExclusive || array[i1] <= array[i2])) {
                temp[i] = array[i1];
                i1++;
            } else {
                temp[i] = array[i2];
                inversions += mid - i1;
                i2++;
            }
            i++;
        }
        for (i = 0; i < toExclusive - fromInclusive; i++) {
            array[i + fromInclusive] = temp[i];
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
