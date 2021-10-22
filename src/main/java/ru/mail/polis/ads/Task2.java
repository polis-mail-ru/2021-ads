package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    public static class Heap {

        public Heap(final int CAPACITY) {
            this.data = new int[CAPACITY];
            capacity = CAPACITY;
            size = 0;
        }

        public void insert(int x) {
            if ((size + 1) == capacity) {
                expandArray();
            }
            data[++size] = x;
            swim(size);
        }

        public int extract() {
            int temp = data[1];
            swap(1, size);
            size--;
            sink(1);
            return temp;
        }

        private void swim(int k) {
            while ((k > 1) && (data[k] > data[k / 2])) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        public void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && data[j] < data[j + 1]) {
                    j++;
                }
                if (data[k] >= data[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int firstIndex, int secondIndex) {
            if ((firstIndex > size) || (secondIndex > size) || (firstIndex < 0) || (secondIndex < 0)) {
                throw new IndexOutOfBoundsException();
            }
            int temp = data[firstIndex];
            data[firstIndex] = data[secondIndex];
            data[secondIndex] = temp;
        }

        private void expandArray() {
            int[] temp = new int[capacity + (capacity >> 1)];
            System.arraycopy(data, 0, temp, 0, capacity);
            capacity += (capacity >> 1);
            data = temp;
        }

        private int[] data;
        private int size;
        private int capacity;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Heap heap = new Heap(16);
        while (count-- > 0) {
            int command = in.nextInt();
            switch (command) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
                    break;
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
