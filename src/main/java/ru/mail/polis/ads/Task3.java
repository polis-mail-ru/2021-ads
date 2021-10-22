package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {

    private Task3() {
        // Should not be instantiated
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap minHeap = new Heap(16, (Integer a, Integer b) -> {
            if(a > b)
                return -1;
            else if (a < b)
                return 1;
            else
                return 0;
        });
        Heap maxHeap = new Heap(16, (Integer a, Integer b) -> {
            if(a > b)
                return 1;
            else if (a < b)
                return -1;
            else
                return 0;
        });
        int[] temp = new int[2];
        String input = in.next();
        if(input == null){
            return;
        }
        temp[0] = Integer.parseInt(input);
        out.println(temp[0]);

        input = in.next();
        if(input == null){
            return;
        }
        temp[1] = Integer.parseInt(input);
        minHeap.insert(Math.max(temp[0], temp[1]));
        maxHeap.insert(Math.min(temp[0], temp[1]));

        int counter = 2;
        int median = (minHeap.peek() + maxHeap.peek()) / 2;
        out.println(median);

        while ((input = in.next()) != null) {
            int newValue = Integer.parseInt(input);
            if (counter % 2 == 0) {
                if (newValue > median) {
                    minHeap.insert(newValue);
                    median = minHeap.extract();
                } else {
                    maxHeap.insert(newValue);
                    median = maxHeap.extract();

                }
            } else {
                if (newValue > median) {
                    minHeap.insert(newValue);
                    maxHeap.insert(median);
                } else {
                    maxHeap.insert(newValue);
                    minHeap.insert(median);
                }
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            }
            counter++;
            out.println(median);
        }
        counter++;
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
