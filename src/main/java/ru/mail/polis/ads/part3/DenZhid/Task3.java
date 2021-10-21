package ru.mail.polis.ads.part3.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task3 {

    private static class Heap {
        private final int[] arr;
        private int size = 0;
        private final boolean isMax;

        public Heap(int size, boolean isMax) {
            arr = new int[size + 1];
            this.isMax = isMax;
        }

        private void insert(int x) {
            arr[++size] = x;
            swim(size);
        }

        public int extract() {
            int max = arr[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        private void swim(int index) {
            int k = index;
            while (k > 1 && (isMax && arr[index] > arr[index / 2] || !isMax && arr[index] < arr[index / 2])) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && (isMax && arr[index] < arr[index + 1] || !isMax && arr[index] > arr[index + 1])) {
                    j++;
                }
                if (isMax && arr[k] >= arr[j] || !isMax && arr[k] <= arr[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        
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
