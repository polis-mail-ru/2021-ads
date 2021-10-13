package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Main {
    private static class Heap {
        private final int[] data;
        private final int size;
        private final boolean isMaxHeap;
        private int current;

        public Heap(int size, boolean isMaxHeap) {
            this.isMaxHeap = isMaxHeap;
            size++;
            data = new int[size];

            this.size = size;
            current = 0;
        }

        public int getSize() {
            return current;
        }

        public int peek() {
            return data[1];
        }

        public void insert(int value) {
            if (current + 1 < size) {
                data[++current] = value;
                swim(current);
            }
        }

        public int extract() {
            if (current != 0) {
                int result = data[1];
                swap(1, current--);
                sink(1);
                return result;
            }
            throw new IndexOutOfBoundsException();
        }

        private void swim(int index) {
            while (index > 1 && saveInvariant(index, index / 2)) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private boolean saveInvariant(int index1, int index2) {
            if (isMaxHeap && data[index1] >= data[index2]) {
                return true;
            } else return !isMaxHeap && data[index1] <= data[index2];
        }

        private void swap(int index, int i) {
            int tmp = data[index];
            data[index] = data[i];
            data[i] = tmp;
        }

        private void sink(int index) {
            while (2 * index <= current) {
                int j = 2 * index;
                if (j < current && !saveInvariant(j, j + 1)) j++;
                if (saveInvariant(index, j)) {
                    break;
                }
                swap(index, j);
                index = j;
            }
        }

    }

    private Main() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(1_000_0001, true);
        Heap minHeap = new Heap(1_000_0001, false);
        String str = in.nextLine();
        int number;
        while (str != null && !str.isEmpty()) {
            number = Integer.parseInt(str);

            if ((minHeap.getSize() + maxHeap.getSize() + 1) % 2 == 0) {
                minHeap.insert(number);
            } else {
                maxHeap.insert(number);
            }


            if (minHeap.getSize() > 0 && maxHeap.peek() > minHeap.peek()) {
                maxHeap.insert(minHeap.extract());
                minHeap.insert(maxHeap.extract());
            }

            if (maxHeap.getSize() == minHeap.getSize()) {
                out.println((maxHeap.peek() + minHeap.peek()) / 2);
            } else if (maxHeap.getSize() > minHeap.getSize()) {
                out.println(maxHeap.peek());
            } else {
                out.println(minHeap.peek());
            }

            str = in.nextLine();
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
        }
    }
}
