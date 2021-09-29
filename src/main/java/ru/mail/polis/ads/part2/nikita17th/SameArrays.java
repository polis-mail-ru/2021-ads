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
public final class SameArrays {

    private SameArrays() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] firstArray = new int[n];
        for (int i = 0; i < n; i++) {
            firstArray[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] secondArray = new int[m];
        for (int i = 0; i < m; i++) {
            secondArray[i] = in.nextInt();
        }

        quickSort(firstArray, 0, n);
        quickSort(secondArray, 0, m);

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (firstArray[i++] != secondArray[j++]) {
                out.println("NO");
                return;
            }
            while (i < n && firstArray[i - 1] == firstArray[i]) {
                i++;
            }
            while (j < m && secondArray[j - 1] == secondArray[j]) {
                j++;
            }
        }

        if (i != n || j != m) {
            out.println("NO");
            return;
        }
        out.println("YES");
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
