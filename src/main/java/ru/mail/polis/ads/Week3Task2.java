package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public final class Week3Task2 {
    private Week3Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Heap data = new Heap(count);
        for (int i = 0; i < count; i++) {
            int command = in.nextInt();
            if (command == 0) {
                data.add(in.nextInt());
                continue;
            }
            out.println(data.pop());
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

        public int pop() {
            int max = data[1];
            data[1] = data[count];
            data[count--] = 0;
            siftDown(1);
            return max;
        }

        private void siftUp(int index) {
            int k = index;
            while (k > 1 && data[k] > data[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void siftDown(int index) {
            int k = index;
            while (2 * k <= count) {
                int j = 2 * k; // left child
                if (j < count && data[j] < data[j + 1]) j++; //right
                if (data[k] >= data[j]) return;
                swap(k, j);
                k = j;
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
