package ru.mail.polis.ads.part3.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 10166. Max куча
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        MaxHeap heap = new MaxHeap(n);
        for (int i = 0; i < n; i++) {
            heap.insert(in.nextInt());
        }
        for (int i = MaxHeap.ROOT_INDEX; i <= heap.length(); i++) {
            out.println(heap.array[i]);
        }
    }

    private static class MaxHeap {
        private final int[] array;
        private int last_index;
        private static final Invariant invariant = (parent, child) -> parent >= child;

        private final static int ROOT_INDEX = 1;

        /**
         * @param size maximum size of the heap
         */
        public MaxHeap(int size) {
            array = new int[size + ROOT_INDEX];
        }

        public void insert(int x) {
            array[++last_index] = x;
            siftUp(last_index);
        }

        private void siftUp(int index) {
            int k = index;
            while (k > ROOT_INDEX && !invariant.holds(array[parent(k)], array[k])) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        private void swap(int left_index, int right_index) {
            int tmp = array[left_index];
            array[left_index] = array[right_index];
            array[right_index] = tmp;
        }

        private static int parent(int child_index) {
            return child_index / 2;
        }

        public int length() {
            return last_index - ROOT_INDEX + 1;
        }

        public interface Invariant {
            boolean holds(int parent_value, int child_value);
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
