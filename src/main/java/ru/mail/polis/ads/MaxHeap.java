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
public final class MaxHeap {
    private MaxHeap() {
        // Should not be instantiated
    }

    private static int[] heap;
    private static int size;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        heap = new int[n];
        for (int i = 0; i < n; i++) {
            insert(in.nextInt());
        }

        for (int i = 0; i < n; i++) {
            out.printf("%d ", heap[i]);
        }
    }

    private static void swap(int first, int second) {
        int tmp = heap[first];
        heap[first] = heap[second];
        heap[second] = tmp;
    }

    private static void insert(int element) {
        heap[size++] = element;
        siftUp(size - 1);
    }

    private static void siftUp(int i) {
        while (i >= 1 && heap[i] > heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
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
