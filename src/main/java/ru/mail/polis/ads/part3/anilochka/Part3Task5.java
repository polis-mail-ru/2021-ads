package ru.mail.polis.ads.part3.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part3Task5 {
    private Part3Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        for (int i = 1; i < n + 1; i++) {
            out.print(array[i] + " ");
        }
    }

    public static void heapSort(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            int tmp = array[1];
            array[1] = array[n];
            array[n] = tmp;
            n--;
            sink(array,1, n);
        }
    }

    private static void sink(int[] array, int i, int size) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && array[j] < array[j + 1]) {
                j++;
            }
            if (array[i] >= array[j]) {
                break;
            }
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i = j;
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
