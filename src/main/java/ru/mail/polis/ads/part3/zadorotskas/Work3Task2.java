package ru.mail.polis.ads.part3.zadorotskas;

import java.io.*;
import java.util.StringTokenizer;


public class Work3Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);

        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
            }
        }
    }

    private static class Heap {
        private int n = 0;
        private final int[] array;

        public Heap(int size) {
            array = new int[size + 1];
        }

        public void insert(int v) {
            array[++n] = v;
            swim(n);
        }

        public int extract() {
            int max = array[1];
            swap(array, 1, n--);
            sink(1);
            return max;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j] < array[j + 1]) j++;

                if (array[k] >= array[j]) break;

                swap(array, k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
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
