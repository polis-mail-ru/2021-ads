package part3.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class SecondTask {

    private static final int MAX = 100000;

    private SecondTask() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(checkCount(in.nextInt()));
        for (int i = 0; i < heap.array.length; i++) {
            int command = in.nextInt();
            switch (command) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
                    break;
                default:
                    throw new IllegalArgumentException("The entered number does not match the condition.\n");
            }
        }
    }

    private static class Heap {
        private int[] array;
        private int count;

        public Heap(int size) {
            array = new int[size];
        }

        public void insert(int value) {
            array[count++] = value;
            swim(count - 1);
        }

        private void swim(int i) {
            for (; i >= 1 && array[i] > array[(i - 1) / 2]; i = (i - 1) / 2) {
                swap(i, (i - 1) / 2);
            }
        }

        public int extract() {
            int top = array[0];
            swap(0, --count);
            sink(0);
            return top;
        }

        private void sink(int k) {
            while (2 * k + 1 < count) {
                int j = 2 * k + 1;
                if (j + 1 < count && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[k] >= array[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            if (i != j) {
                array[j] ^= array[i];
                array[i] ^= array[j];
                array[j] ^= array[i];
            }
        }
    }

    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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