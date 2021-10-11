package ru.mail.polis.ads.part3.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part3Task3 {
    private Part3Task3() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int MAX_N = 1000000;
        MaxHeap maxHeap = new MaxHeap(MAX_N / 2);
        MinHeap minHeap = new MinHeap(MAX_N / 2);

        int med = 0;
        if (in.hasNextInt()) {
            med = in.nextInt();
            out.println(med);
        }
        int tmp;
        while (in.hasNextInt()) {
            tmp = in.nextInt();
            // med == -1, когда последовательность содержит четное количество чисел
            if (med == -1) {
                if (maxHeap.getRoot() > tmp) {
                    maxHeap.insert(tmp);
                    med = maxHeap.exctract();
                } else {
                    minHeap.insert(tmp);
                    med = minHeap.exctract();
                }
                out.println(med);
                continue;
            }
            if (med > tmp) {
                maxHeap.insert(tmp);
                minHeap.insert(med);
            } else {
                minHeap.insert(tmp);
                maxHeap.insert(med);
            }
            med = -1;
            out.println((maxHeap.getRoot() + minHeap.getRoot()) / 2);
        }
    }

    public static class MaxHeap {
        private int[] array;
        private int size;

        public MaxHeap(int n) {
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

        public int getRoot() {
            return array[1];
        }
    }

    public static class MinHeap {
        private int[] array;
        private int size;

        public MinHeap(int n) {
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
            while (i > 1 && array[i] < array[i / 2]) {
                int tmp = array[i];
                array[i] = array[i / 2];
                array[i / 2] = tmp;
                i /= 2;
            }
        }

        private void sink(int i) {
            while (2 * i <= size) {
                int j = 2 * i;
                if (j < size && array[j] > array[j + 1]) {
                    j++;
                }
                if (array[i] <= array[j]) {
                    break;
                }
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i = j;
            }
        }

        public int getRoot() {
            return array[1];
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
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
