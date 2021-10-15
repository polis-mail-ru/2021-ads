import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9549878
 */
public final class Week3Task5 {
    private Week3Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        Heap heap = new Heap(N, (o1, o2) -> o2 - o1);
        for (int i = 0; i < N; ++i) {
            heap.insert(in.nextInt());
        }

        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = heap.extract();
        }

        for (int i = 0; i < N; ++i) {
            out.print(array[i] + " ");
        }
    }

    private static class Heap {
        private int[] array;
        private int capacity;
        private int size;
        private Comparator<Integer> invariant;

        Heap(int capacity, Comparator<Integer> invariant) {
            this.array = new int[capacity + 1];
            this.capacity = capacity;
            this.size = 0;
            this.invariant = invariant;
        }

        public void insert(int newElement) {
            array[++size] = newElement;
            swim(size);
        }

        public int extract() {
            int max = array[1];

            int temp = array[1];
            array[1] = array[size];
            array[size] = temp;

            size--;

            sink(1);

            return max;
        }

        public int peek() {
            return array[1];
        }

        private void swim(int k) {
            int index = k;

            while (index > 1 && invariant.compare(array[index], array[index / 2]) > 0) {
                int temp = array[index];
                array[index] = array[index / 2];
                array[index / 2] = temp;

                index /= 2;
            }
        }

        private void sink(int k) {
            int index = k;

            while (2 * index <= size) {
                int left = 2 * index;

                if (left < size && invariant.compare(array[left], array[left + 1]) < 0) {
                    left++;
                }
                if (invariant.compare(array[index], array[left]) >= 0) {
                    break;
                }

                int temp = array[index];
                array[index] = array[left];
                array[left] = temp;

                index = left;
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
