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
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            int action = in.nextInt();
            if (action == 0) {
                heap.insert(in.nextInt());
            } else if (action == 1) {
                out.println(heap.extract());
            }
        }
    }

    private static class Heap {
        private static final int CAPACITY = 100001;
        private int[] arr = new int[CAPACITY];
        private int maxIndex = 0;

        public void insert(int number) {
            arr[++maxIndex] = number;
            swim(maxIndex);
        }

        public int extract() {
            int max = arr[1];
            swap(1, maxIndex--);
            sink(1);
            return max;
        }

        private void swim(int k) {
            int index = k;
            while ((index > 1) && (arr[index] > arr[index / 2])) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void sink(int k) {
            int index = k;
            while (2 * index <= maxIndex) {
                int j = 2 * index;
                if ((j < maxIndex) && (arr[j] < arr[j + 1])) {
                    j++;
                }
                if (arr[index] >= arr[j]) {
                    break;
                }
                swap(index, j);
                index = j;
            }
        }

        private void swap(int a, int b) {
            arr[a] ^= arr[b];
            arr[b] ^= arr[a];
            arr[a] ^= arr[b];
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
