import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Задача №767. Похожие массивы
 * <p>
 * Memory O(N + M)
 * <p>
 * Time O(N^2 + M^2)
 */
public final class Week2Task5 {
    private Week2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final IntArraySet setA = new IntArraySet(in);
        final IntArraySet setB = new IntArraySet(in);
        out.println(setA.equals(setB) ? "YES" : "NO");
    }

    private static class IntArraySet {
        private final int[] array;
        private int length;

        public IntArraySet(int capacity) {
            array = new int[capacity];
            length = 0;
        }

        public IntArraySet(FastScanner in) {
            this(in.nextInt());
            for (int i = 0; i < array.length; i++) {
                add(in.nextInt());
            }
        }

        public int length() {
            return length;
        }

        public void add(int item) {
            if (length >= array.length) {
                // set is full
                return;
            }
            final int position = lowerBound(0, length, item);
            if (position >= length) {
                array[length++] = item;
                return;
            }
            if (array[position] == item) {
                return;
            }
            System.arraycopy(array, position, array, position + 1, length - position);
            array[position] = item;
            length++;
        }

        public boolean equals(IntArraySet that) {
            if (this == that) return true;
            return length == that.length
                    && Arrays.equals(array, 0, length, that.array, 0, that.length);
        }

        /**
         * min i in [begin; begin+step) such that this.array[i] >= item;
         * begin + step if no such i exists
         */
        private int lowerBound(int begin, int step, int item) {
            if (step <= 0) {
                return begin;
            }

            final int compare = Integer.compare(array[begin + step / 2], item);
            if (compare == 0) {
                return begin + step / 2;
            }
            if (compare < 0) {
                return lowerBound(begin + step / 2 + 1, (step - 1) / 2, item);
            }
            return lowerBound(begin, step / 2, item);
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
