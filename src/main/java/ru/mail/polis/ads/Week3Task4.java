package ru.mail.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class Week3Task4 {
    private Week3Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Heap heap = new Heap(count);

        for (int i = 0; i < count; i++) {
            heap.add(in.nextInt());
        }

        for (int i = 1; i <= count; i++) {
            out.print(heap.get(i));
            if (i != count) {
                out.print(" ");
            }
        }

    }

    private static class Heap {

        private int[] data;
        private int count;

        Heap(int size) {
            data = new int[size + 1];
        }

        public void add(int element) {
            data[++count] = element;
            siftUp(count);
        }

        public int get(int index) {
            return data[index];
        }

        private void siftUp(int index) {
            int k = index;
            while (k > 1 && data[k] > data[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void swap(int i1, int i2) {
            int temp = data[i1];
            data[i1] = data[i2];
            data[i2] = temp;
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
