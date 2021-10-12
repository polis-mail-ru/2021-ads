package ru.mail.polis.ads.part3.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 4039. Хипуй
 * <p>
 * https://www.e-olymp.com/ru/submissions/9513124
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    final static int INSERT_COMMAND = 0;
    final static int EXTRACT_COMMAND = 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            switch (in.nextInt()) {
                case INSERT_COMMAND:
                    heap.insert(in.nextInt());
                    break;
                case EXTRACT_COMMAND:
                    out.println(heap.extract());
                    break;
            }
        }
    }

    private static class Heap {
        int[] array;
        int last_index;

        public Heap(int size) {
            array = new int[size + 1];
        }

        public void insert(int x) {
            array[++last_index] = x;
            siftUp(last_index);
        }

        private void siftUp(int index) {
            int k = index;
            while (k > 1 && array[k] > array[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int left_index, int right_index) {
            int tmp = array[left_index];
            array[left_index] = array[right_index];
            array[right_index] = tmp;
        }

        public int extract() {
            int max = array[1];
            swap(1, last_index--);
            siftDown(1);
            return max;
        }

        private void siftDown(int index) {
            int parent_index = index;
            while (2 * parent_index <= last_index) {
                final boolean choose_left = 2 * parent_index == last_index
                        || array[2 * parent_index] >= array[2 * parent_index + 1];
                final int max_child_index = choose_left ? 2 * parent_index : 2 * parent_index + 1;
                if (array[parent_index] >= array[max_child_index]) {
                    break;
                }
                swap(parent_index, max_child_index);
                parent_index = max_child_index;
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
