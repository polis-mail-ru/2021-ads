package ru.mail.polis.ads.part2.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem solution template.
 */
public final class Different {
    private Different() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        quickSort(array, 0, n);

        int counter = 1;
        int prevElement = array[0];
        for (int i = 1; i < n; i++) {
            if (prevElement != array[i]) {
                counter++;
            }

            prevElement = array[i];
        }

        out.println(counter);
    }

    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = randomPartition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    private static void swap(int[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

    private static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivot = array[fromInclusive];
        int i = fromInclusive;

        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j] <= pivot) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);

        return i;
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
