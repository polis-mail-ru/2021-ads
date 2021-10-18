package ru.mail.polis.ads.part3.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(Integer::compareTo);
        Heap minHeap = new Heap((a, b) -> b - a);
        try {
            while (true) {
                int number = in.nextInt();
                if (maxHeap.getSize() == minHeap.getSize()) {
                    if (maxHeap.getTop() > number) {
                        maxHeap.insert(number);
                        out.println(maxHeap.getTop());
                    } else {
                        minHeap.insert(number);
                        out.println(minHeap.getTop());
                    }
                } else if (maxHeap.getSize() > minHeap.getSize()) {
                    if (maxHeap.getTop() > number) {
                        minHeap.insert(maxHeap.extract());
                        maxHeap.insert(number);
                    } else {
                        minHeap.insert(number);
                    }
                    out.println((maxHeap.getTop() + minHeap.getTop()) / 2);
                } else {
                    if (minHeap.getTop() < number) {
                        maxHeap.insert(minHeap.extract());
                        minHeap.insert(number);
                    } else {
                        maxHeap.insert(number);
                    }
                    out.println((maxHeap.getTop() + minHeap.getTop()) / 2);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    private static class Heap {
        private static final int CAPACITY = 1000001;
        private int[] arr = new int[CAPACITY];
        private int maxIndex = 0;
        private final Comparator<Integer> comparator;

        public Heap(Comparator<Integer> comparator) {
            this.comparator = comparator;
        }

        public int getSize() {
            return maxIndex;
        }

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

        public int getTop() {
            return arr[1];
        }

        private void swim(int k) {
            int index = k;
            while ((index > 1) && (comparator.compare(arr[index], arr[index / 2]) > 0)) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void sink(int k) {
            int index = k;
            while (2 * index <= maxIndex) {
                int j = 2 * index;
                if ((j < maxIndex) && (comparator.compare(arr[j], arr[j + 1]) < 0)) {
                    j++;
                }
                if (comparator.compare(arr[index], arr[j]) >= 0) {
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
