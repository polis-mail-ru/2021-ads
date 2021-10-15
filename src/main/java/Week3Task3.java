import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9548720
 */
public final class Week3Task3 {
    private Week3Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int MAX_SIZE = 1000000;

        Heap maxHeap = new Heap(MAX_SIZE / 2, (o1, o2) -> o1 - o2);
        Heap minHeap = new Heap(MAX_SIZE / 2, (o1, o2) -> o2 - o1);

        String newElement;
        while ((newElement = in.nextString()) != null) {
            int newInt = Integer.parseInt(newElement);

            if (minHeap.size() == 0) {
                minHeap.insert(newInt);
            } else {
                double median = 0;
                if (minHeap.size() == maxHeap.size()) {
                    median = (minHeap.peek() + maxHeap.peek()) / 2.0;
                } else if (minHeap.size() > maxHeap.size()) {
                    median = minHeap.peek();
                } else {
                    median = maxHeap.peek();
                }

                if (newInt < median) {
                    maxHeap.insert(newInt);
                } else {
                    minHeap.insert(newInt);
                }
            }

            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.insert(minHeap.extract());
            } else if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.insert(maxHeap.extract());
            }

            int median = 0;
            if (minHeap.size() == maxHeap.size()) {
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            } else if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
            } else {
                median = maxHeap.peek();
            }

            out.println(median);
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

        public int size() {
            return size;
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

        String nextString() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
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
