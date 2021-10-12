package ru.mail.polis.ads.part3.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 4074. Найти медиану 2
 * <p>
 * https://www.e-olymp.com/ru/submissions/9520363
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static int MAX_SIZE = 1_000_000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input;
        DynamicMedian median = new DynamicMedian();
        while ((input = in.nextOrNull()) != null) {
            median.consume(Integer.parseInt(input));
            out.println(median.get());
        }
    }

    private static class DynamicMedian {
        // all elements <= median
        final Heap smaller = new Heap(MAX_SIZE / 2, (parent, child) -> parent >= child);
        // all elements > median
        final Heap greater = new Heap(MAX_SIZE / 2, (parent, child) -> parent <= child);

        void consume(int x) {
            final int byLength = Integer.compare(smaller.length(), greater.length());
            final int fromMedian = Integer.compare(x, get());
            if (byLength < 0) {
                if (fromMedian <= 0) {
                    smaller.insert(x);
                    return;
                }
                smaller.insert(greater.extract());
                greater.insert(x);
                return;
            }
            if (fromMedian >= 0) {
                greater.insert(x);
                return;
            }
            if (byLength > 0) {
                greater.insert(smaller.extract());
            }
            smaller.insert(x);
        }

        int get() {
            final int byLength = Integer.compare(smaller.length(), greater.length());
            if (byLength > 0) {
                return smaller.root();
            }
            if (byLength < 0) {
                return greater.root();
            }
            return (smaller.root() + greater.root()) / 2;
        }
    }

    private static class Heap {
        final int[] array;
        int last_index;
        final Invariant invariant;

        final static int ROOT_INDEX = 1;

        /**
         * @param size maximum size of the heap
         */
        public Heap(int size, Invariant invariant) {
            array = new int[size + ROOT_INDEX];
            this.invariant = invariant;
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

        private static int left(int parent_index) {
            return 2 * parent_index;
        }

        private static int right(int parent_index) {
            return 2 * parent_index + 1;
        }

        public int root() {
            return array[ROOT_INDEX];
        }

        public int length() {
            return last_index - ROOT_INDEX + 1;
        }

        public int extract() {
            int top = root();
            swap(ROOT_INDEX, last_index--);
            siftDown(ROOT_INDEX);
            return top;
        }

        private void siftDown(int index) {
            int parent_index = index;
            int left_index;
            while ((left_index = left(parent_index)) <= last_index) {
                final int right_index = right(parent_index);
                final boolean choose_left = left_index == last_index
                        || invariant.holds(array[left_index], array[right_index]);
                final int max_child_index = choose_left ? left_index : right_index;
                if (invariant.holds(array[parent_index], array[max_child_index])) {
                    break;
                }
                swap(parent_index, max_child_index);
                parent_index = max_child_index;
            }
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

        /**
         * Same as next(), except it returns null if end of the input is reached
         */
        String nextOrNull() {
            try {
                return next();
            } catch (NullPointerException e) {
                // end of input
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
        }
    }
}
