package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part3Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        for (int i = 1; i < n; ++i) {
            out.print(array[i] + " ");
        }
        out.print(array[n]);
    }

    private static void heapSort(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
    }



    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static void sink(int[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j + 1]) {
                j++;
            }
            if (array[k] >= array[j]) {
                break;
            }
            swap(array, k, j);
            k = j;
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
