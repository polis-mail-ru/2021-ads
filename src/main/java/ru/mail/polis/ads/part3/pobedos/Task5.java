package ru.mail.polis.ads.part3.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        for (int i = 1; i < n + 1; i++) {
            out.print(arr[i] + " ");
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(arr, k, n);
        }
        while (n > 1) {
            swap(arr, 1, n--);
            sink(arr, 1, n);
        }
    }


    private static void sink(int[] arr, int k, int maxIndex) {
        int index = k;
        while (2 * index <= maxIndex) {
            int j = 2 * index;
            if ((j < maxIndex) && (arr[j] < arr[j + 1])) {
                j++;
            }
            if (arr[index] >= arr[j]) {
                break;
            }
            swap(arr, index, j);
            index = j;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
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
