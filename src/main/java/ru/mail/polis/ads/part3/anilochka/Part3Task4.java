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
public final class Part3Task4 {
    private Part3Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            heap.insert(in.nextInt());
        }
        for (int i = 1; i < heap.getSize() + 1; i++) {
            out.print(heap.getArray()[i] + " ");
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
            for (int i = size; i > 1; i--) {
                if (array[i] > array[i / 2]) {
                    swim(i);
                }
            }
        }

        public int exctract() {
            int res = array[1];
            array[1] = array[size];
            array[size--] = 0;
            for (int i = 1; i < size; i++) {
                if (array[i] < array[i * 2] || array[i] < array[i * 2 + 1]) {
                    sink(i);
                }
            }
            return res;
        }

        private void swim(int i) {
            int tmp = array[i];
            array[i] = array[i / 2];
            array[i / 2] = tmp;
        }

        private void sink(int i) {
            int tmp = array[i];
            if (tmp < array[i * 2]) {
                array[i] = array[i * 2];
                array[i * 2] = tmp;
                return;
            }
            array[i] = array[i * 2 + 1];
            array[i * 2 + 1] = tmp;
        }

        public int[] getArray() {
            return array;
        }

        public int getSize() {
            return size;
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
