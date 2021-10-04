package ru.mail.polis.ads.part2.tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class SecondTask {

    private static final int DIFF = 107;
    private static final int MAX = 100000;

    private SecondTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = IntStream.range(0, checkCount(in.nextInt())).map(i -> in.nextInt()).toArray();
        mergeSort(array, 0, array.length - 1);
        Arrays.stream(array).mapToObj(j -> j + " ").forEach(out::print);
    }

    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
    }

    private static void mergeSort(int[] array, int from, int to) {
        if (from == to) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        mergeSort(array, from, mid);
        mergeSort(array, mid + 1, to);
        merge(array, from, mid, to);

    }

    private static void merge(int[] array, int from, int middle, int to) {
        int p = from;
        int q = middle + 1;
        int k = 0;
        int a[] = new int[to - from + 1];
        for (int i = from; i < to + 1; i++) {
            if (p > middle) {
                a[k++] = array[q++];
            } else if (q > to) {
                a[k++] = array[p++];
            } else if (array[p] < array[q]) {
                a[k++] = array[p++];
            } else {
                a[k++] = array[q++];
            }
        }
        for (int i = 0; i < k; i++) {
            array[from++] = a[i];
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
