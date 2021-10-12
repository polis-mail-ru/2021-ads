package task2;

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
        private int current;

        public Heap(int size) {
            size++;
            data = new int[size];
            this.size = size;
            current = 0;
        }

        public void insert(int value) {
            if (current < size) {
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
            while (index > 1 && data[index] > data[index / 2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void swap(int index, int i) {
            int tmp = data[index];
            data[index] = data[i];
            data[i] = tmp;
        }

        private void sink(int index) {
            while (2 * index <= current) {
                int j = 2 * index;
                if (j < current && data[j] < data[j + 1]) j++;
                if (data[index] >= data[j]) {
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
        int n = in.nextInt();
        int op, number;
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            op = in.nextInt();
            if (op == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
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
