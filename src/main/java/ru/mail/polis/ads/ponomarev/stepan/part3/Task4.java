package ru.mail.polis.ads.ponomarev.stepan.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    private static class Heap {
        private int N;
        private final int[] data;

        public Heap(int maxSize) {
            this.N = 0;
            this.data = new int[maxSize + 1];
        }

        public void insert(int value) {
            N++;
            data[N] = value;
            swim(N);
        }

        public int getMax() {
            int value = data[1];
            data[1] = data[N];
            N--;
            data[N + 1] = -1;

            sink(1);

            return value;
        }

        private void swim(int k) {
            while (k > 1) {
                if (data[k / 2] < data[k]) {
                    swap(k / 2, k);
                    k = k / 2;
                } else {
                    break;
                }
            }
        }

        private void sink(int k) {
            while (k * 2 <= N) {
                int j = k * 2;
                if (j < N && data[j] < data[j + 1]) {
                    j++;
                }

                if (data[k] < data[j]) {
                    swap(k, j);
                    k = j;
                } else {
                    break;
                }

            }
        }

        private void swap(int firstIndex, int secondIndex) {
            if (firstIndex >= data.length || secondIndex >= data.length) {
                throw new IllegalArgumentException("Invalid index");
            }

            int tmp = data[firstIndex];
            data[firstIndex] = data[secondIndex];
            data[secondIndex] = tmp;
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final int k = in.nextInt();

        final Heap heap = new Heap(k);

        for (int i = 0; i < k; i++) {
            heap.insert(in.nextInt());
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < k; i++) {
            str.append(heap.getMax());

            if (i != k-1) {
              str.append(" ");
            }
        }

        out.println(str);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}