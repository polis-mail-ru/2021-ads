package part3.tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class FourthTask {

    private static final int MAX = 1000;

    private FourthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(checkCount(in.nextInt()));
        IntStream.range(0, heap.array.length).map(i -> in.nextInt()).forEach(heap::insert);
        heap.print(out);
    }

    private static class Heap {
        private int[] array;
        private int count;

        public Heap(int size) {
            array = new int[size];
        }

        public Heap(int[] array) {
            this(array.length);
            Arrays.stream(array).forEach(this::insert);
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

        private void swap(int i, int j) {
            if (i != j) {
                array[j] ^= array[i];
                array[i] ^= array[j];
                array[j] ^= array[i];
            }
        }

        public void print(final PrintWriter out) {
            IntStream.range(0, array.length)
                    .mapToObj(i -> array[i] + (i != array.length - 1 ? " " : "")).forEach(out::print);
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
