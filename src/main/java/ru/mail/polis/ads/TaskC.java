package ru.mail.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */


public final class TaskC {

    private static class Heap {
        private int[] a;
        private int size;
        private final int capacity;
        private final boolean isMax;

        public Heap(int capacity, boolean isMax) {
            this.size = 0;
            a = new int[capacity + 1];
            this.capacity = capacity + 1;
            this.isMax = isMax;
        }

        private void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && ((isMax && a[k] > a[k / 2]) || (!isMax && a[k] < a[k / 2]))) {
                swap(k, k / 2);
                k /=     2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k; // left child
                if (j < size && ((isMax && a[j] < a[j + 1]) || (!isMax && a[j] > a[j + 1]))) {
                    j++; // right child
                }
                if (((isMax && a[k] >= a[j]) || (!isMax && a[k] <= a[j]))) {
                    break; // invariant holds
                }
                swap(k, j);
                k = j;
            }
        }

        public void insert(int x) {
            a[++size] = x;
            swim(size);
        }

        public int extract() {
            int value = a[1];
            swap(1, size--);
            sink(1);
            return value;
        }

        public int peek() {
            return a[1];
        }

        public int size() {
            return size;
        }
    }

    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap minHeap = new Heap(500000, false);
        Heap maxHeap = new Heap(500000, true);
        String s = in.nextLine();
        if(s.isEmpty()) {
            return;
        }
        int input = Integer.parseInt(s);
        minHeap.insert(input);
        System.out.println(input);
        s = in.nextLine();
        boolean isEven = false;
        while (!s.isEmpty()) {
            input = Integer.parseInt(s);
            if(isEven) {
                minHeap.insert(input);
            } else {
                maxHeap.insert(input);
            }
            isEven = !isEven;
            if (maxHeap.peek() > minHeap.peek()) {
                maxHeap.insert(minHeap.extract());
                minHeap.insert(maxHeap.extract());
            }

            if (maxHeap.size() == minHeap.size()) {
                System.out.println((maxHeap.peek() + minHeap.peek()) / 2);
            } else if (maxHeap.size() > minHeap.size()) {
                System.out.println(maxHeap.peek());
            } else {
                System.out.println(minHeap.peek());
            }
            s = in.nextLine();
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
        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
