package ru.mail.polis.ads.part3.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */

public final class Week3Test2 {
    private Week3Test2() {
        // Should not be instantiated
    }
    
    static class ArrayHeap {
        int[] array;

        // I decided to put the current size into array[0]
        public ArrayHeap(int size) {
            this.array = new int[size + 1];
            this.array[0] = 0;
        }

        // xor swap hack due to the fact that any value in the range of [1, 10^7]
        static final void swap(final int[] array, final int pos1, final int pos2) {
            if (array[pos1] == array[pos2]) {
                return;
            }
            array[pos1] ^= array[pos2];
            array[pos2] ^= array[pos1];
            array[pos1] ^= array[pos2];

        }

        void swim(int pos) {
            int j;
            while (pos > 1 && this.array[pos] > this.array[j = pos >> 1]) {
                swap(this.array, pos, j);
                pos = j;
            }
        }

        void sink(int pos) {
            int j;
            while ((j = 2 * pos) <= this.array[0]) {
                if (j < this.array[0] && this.array[j] < this.array[j + 1]) {
                    ++j;
                }
                if (this.array[pos] >= this.array[j]) {
                    break;
                }
                swap(this.array, pos, j);
                pos = j;
            }
        }

        void insert(int value) {
            this.array[++this.array[0]] = value;
            swim(this.array[0]);
        }

        int extract() {
            int root = this.array[1];
            this.array[1] = this.array[this.array[0]--];
            sink(1);
            return root;
        }

    }

    // https://www.e-olymp.com/ru/submissions/9484450
    private static void solve(final FastScanner in, final PrintWriter out) {
        int steps = in.nextInt();
        // don't think that it is possible to get heap for
        // more elements than commands count
        ArrayHeap heap = new ArrayHeap(steps);

        while (steps-- > 0) {
            if (in.nextInt() == 0) {
                heap.insert(in.nextInt());
            } else { // expected that command can be only 0 or 1
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
