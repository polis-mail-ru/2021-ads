package ru.mail.polis.ads.part3.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part3Task2 {
    private static final int INSERT_COMMAND = 0;
    private static final int EXCTRACT_COMMAND = 1;

    private Part3Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        int tmpCommand;
        for (int i = 0; i < n; i++) {
            tmpCommand = in.nextInt();
            switch (tmpCommand) {
                case INSERT_COMMAND:
                    heap.insert(in.nextInt());
                    break;
                case EXCTRACT_COMMAND:
                    out.println(heap.exctract());
                    break;
            }
        }
    }

    public static class Heap {
        private int[] array;
        private int size;

        public Heap(int n) {
            this.array = new int[n + 1];
            this.size = 0;
        }

        public void insert(int x) {
            array[++size] = x;
            swim(size);
        }

        public int exctract() {
            int res = array[1];
            array[1] = array[size];
            array[size--] = 0;
            sink(1);
            return res;
        }

        private void swim(int i) {
            while (i > 1 && array[i] > array[i / 2]) {
                int tmp = array[i];
                array[i] = array[i / 2];
                array[i / 2] = tmp;
                i /= 2;
            }
        }

        private void sink(int i) {
            while (2 * i <= size) {
                int j = 2 * i;
                if (j < size && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[i] >= array[j]) {
                    break;
                }
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i = j;
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
