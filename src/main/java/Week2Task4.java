import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №1418. Разные
 */
public final class Week2Task4 {
    private Week2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        IntArraySet set = new IntArraySet(n);
        for (int i = 0; i < n; i++) {
            set.add(in.nextInt());
        }
        out.println(set.length());
    }

    private static class IntArraySet {
        private final int[] array;
        private int length;

        public IntArraySet(int capacity) {
            array = new int[capacity];
            length = 0;
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
