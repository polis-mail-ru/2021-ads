package ru.artyom.scheredin.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    public final static int DEFAULT_CAPACITY = 16;

    private Task5() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int counter = in.nextInt();
        int[] arr = new int[counter];
        for (int i = 0; i < counter; i++) {
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        Arrays.stream(arr).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void heapSort(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 0; k--) {
            sink(a, k, n);
        }
        while (n > 0) {
            swap(a, 0, n--);
            sink(a, 0, n);
        }
    }

    public static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) j++;
            if (a[k] >= a[j]) break;
            swap(a, k, j);
            k = j;
        }
    }

    public static void swap(int[] data, int firstIndex, int secondIndex) {
        if ((firstIndex > data.length) || (secondIndex > data.length) || (firstIndex < 0) || (secondIndex < 0)) {
            throw new IndexOutOfBoundsException();
        }
        int temp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = temp;
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
                } catch (NullPointerException e) {
                    return null;
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
