package ru.mail.polis.ads.part3.vspochernin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9508953
 */
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] heap;
        private int size;
        private final boolean isMaxHeap;
        private final int capacity;

        public Heap(int capacity, boolean isMaxHeap) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
            this.size = 0;
            this.isMaxHeap = isMaxHeap;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && ((isMaxHeap && heap[k] > heap[k / 2]) || (!isMaxHeap && heap[k] < heap[k / 2]))) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k; // Левый ребенок.
                if (j < size && ((isMaxHeap && heap[j] < heap[j + 1]) || (!isMaxHeap && heap[j] > heap[j + 1]))) {
                    j++; // Правый ребенок.
                }
                if (((isMaxHeap && heap[k] >= heap[j]) || (!isMaxHeap && heap[k] <= heap[j]))) {
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

        public int peek() {
            if (size == 0) {
                return 0;
            }
            return heap[1];
        }

        public int getSize() {
            return size;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(500000, true);
        Heap minHeap = new Heap(500000, false);
        boolean isSeparatedMedian = false;
        int n;
        int median = 0;
        int[] a = new int[3];
        Scanner newIn = new Scanner(System.in);
        if (newIn.hasNextInt()) {
            median = newIn.nextInt();
            isSeparatedMedian = !isSeparatedMedian;
            out.println(median);
        }
        while (newIn.hasNextInt()) {
            n = newIn.nextInt();
            if (isSeparatedMedian) { // Медиана была отдельным элементом.
                if (n > median) {
                    minHeap.insert(n);
                    maxHeap.insert(median);
                } else {
                    minHeap.insert(median);
                    maxHeap.insert(n);
                }
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else { // Медиана была средним арифметическим.
                a[0] = maxHeap.extract();
                a[1] = n;
                a[2] = minHeap.extract();
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                if (a[1] > a[2]) {
                    swap(a, 1, 2);
                }
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                maxHeap.insert(a[0]);
                median = a[1];
                minHeap.insert(a[2]);
            }
            isSeparatedMedian = !isSeparatedMedian;
            out.println(median);
        }

        /*int n = in.nextInt();
        Heap heap = new Heap(n);
        int command;
        for (int i = 0; i < n; i++) {
            command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else if (command == 1) {
                System.out.println(heap.extract());
            }
        }*/
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