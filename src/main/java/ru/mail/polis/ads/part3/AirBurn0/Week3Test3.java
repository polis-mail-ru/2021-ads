package ru.mail.polis.ads.part3.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Problem solution template.
 */
public final class Week3Test3 {
    private Week3Test3() {
        // Should not be instantiated
    }

    static class ArrayHeap {
        final int[] array;

        public ArrayHeap(int size) {
            this.array = new int[size + 1];
            this.array[0] = 0;
        }

        static final void swap(int[] array, int pos1, int pos2) {
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

        int top() {
            return this.array[1];
        }

        int size() {
            return this.array[0];
        }
    }

    static class ArrayHeapMin extends ArrayHeap {

        public ArrayHeapMin(int size) {
            super(size);
        }

        void swim(int pos) {
            int j;
            while (pos > 1 && this.array[pos] < this.array[j = pos >> 1]) {
                swap(this.array, pos, j);
                pos = j;
            }
        }

        void sink(int pos) {
            int j;
            while ((j = 2 * pos) <= this.array[0]) {
                if (j < this.array[0] && this.array[j] > this.array[j + 1]) {
                    ++j;
                }
                if (this.array[pos] <= this.array[j]) {
                    break;
                }
                swap(this.array, pos, j);
                pos = j;
            }
        }

    }

    // https://www.e-olymp.com/ru/submissions/9494214
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayHeap minHeap = new ArrayHeapMin(1000001);
        ArrayHeap maxHeap = new ArrayHeap(1000001);

        int temp;
        String line;
        while ((line = in.next()) != null) {
            temp = Integer.valueOf(line);

            if (maxHeap.top() > temp) {
                maxHeap.insert(temp);
            } else {
                minHeap.insert(temp);
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.insert(maxHeap.extract());
            }
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.insert(minHeap.extract());
            }

            temp = maxHeap.size() - minHeap.size();
            out.println(temp == 0 ? (maxHeap.top() + ((minHeap.top() - maxHeap.top()) >> 1))
                    : (temp > 0 ? maxHeap.top() : minHeap.top()));
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
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
