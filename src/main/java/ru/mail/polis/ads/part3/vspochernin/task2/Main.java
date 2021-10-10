package ru.mail.polis.ads.part3.vspochernin.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9493367
 */
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] heap;
        private int size;
        private final int capacity;

        public Heap(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
            this.size = 0;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && heap[k] > heap[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k; // Левый ребенок.
                if (j < size && heap[j] < heap[j + 1]) {
                    j++; // Правый ребенок.
                }
                if (heap[k] >= heap[j]) {
                    break; // Инвариант восстановился.
                }
                swap(k, j);
                k = j;
            }
        }

        public void insert(int x) {
            heap[++size] = x;
            swim(size);
        }

        public int extract() {
            int max = heap[1];
            swap(1, size--);
            sink(1);
            return max;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        int command;
        for (int i = 0; i < n; i++) {
            command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else if (command == 1) {
                System.out.println(heap.extract());
            }
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