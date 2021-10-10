package ru.mail.polis.ads.part3.vspochernin.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9494369
 */
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] heap;
        private int size;

        public Heap(int[] a) {
            this.size = a.length - 1; // Подразумевается, что будет подан массив с "лишним" нулевым элементом.
            this.heap = a;
            for (int k = size / 2; k >= 1; k--) {
                sink(k);
            }
        }

        public void sort() {
            while (size > 1) {
                swap(1, size--);
                sink(1);
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
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
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        Heap heap = new Heap(a);
        heap.sort();
        for (int i = 1; i < n; i++) {
            System.out.print(a[i]);
            System.out.print(" ");
        }
        System.out.println(a[n]);
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
