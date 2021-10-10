package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
class Heap {
    private int[] a;
    private int size;
    private final int capacity;

    public Heap(int capacity) {
        this.size = 0;
        a = new int[capacity + 1];
        this.capacity = capacity;
    }

    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && a[k] > a[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; // left child
            if (j < size && a[j] < a[j + 1]) {
                j++; // right child
            }
            if (a[k] >= a[j]) {
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
        int max = a[1];
        swap(1, size--);
        sink(1);
        return max;
    }
}

public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
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
