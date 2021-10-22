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
public final class Task4 {
    public final static int DEFAULT_CAPACITY = 16;

    private Task4() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int counter = in.nextInt();
        int[] arr = new int[counter];
        for (int i = 0; i < counter; i++) {
            arr[i] = in.nextInt();
        }
        Heap maxHeap = new Heap(DEFAULT_CAPACITY, (Integer a, Integer b) -> {
            if (a > b)
                return 1;
            else if (a < b)
                return -1;
            else
                return 0;
        }, arr);
        out.println(maxHeap.toString());
    }

    public static class Heap {
        private int[] data;
        private int size;
        private int capacity;
        private final Comparator<Integer> comparator;

        public Heap(final int CAPACITY, Comparator<Integer> comparator) {
            this.data = new int[CAPACITY];
            capacity = CAPACITY;
            size = 0;
            this.comparator = comparator;
        }

        public Heap(final int CAPACITY, Comparator<Integer> comparator, int[] data) {
            this.data = new int[data.length + 1];
            System.arraycopy(data, 0, this.data, 1, data.length);
            capacity = data.length + 1;
            size = data.length;
            this.comparator = comparator;
            for (int k = size / 2; k >= 1; k--) {
                sink(k);
            }
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

        public int peek() {
            if (size == 0) {
                return 0;
            }
            return data[1];
        }

        public int getSize() {
            return size;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(data).skip(1).forEach(x -> stringBuilder.append(x + " "));
            return stringBuilder.toString();
        }

        private void swim(int k) {
            while ((k > 1) && (comparator.compare(data[k], data[k / 2]) > 0)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && (comparator.compare(data[j], data[j + 1]) < 0)) {
                    j++;
                }
                if (comparator.compare(data[k], data[j]) >= 0) {
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
            if (capacity < 2) {
                capacity = 2;
            }
            int[] temp = new int[capacity + (capacity >> 1)];
            System.arraycopy(data, 0, temp, 0, capacity);
            capacity += (capacity >> 1);
            data = temp;
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
