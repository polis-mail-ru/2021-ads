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
public class Task2 {

    private static class Heap {
        private final int[] arr;
        private int size;

        private Heap(int size) {
            arr = new int[size + 1];
        }

        private void insert(int x) {
            arr[++size] = x;
            swim(size);
        }

        private int extract() {
            int max = arr[1];
            swap(arr, 1, size--);
            sink(1);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && arr[k] > arr[k / 2]) {
                swap(arr, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (arr[k] <= arr[j]) {
                    break;
                }
                swap(arr, k, j);
                k = j;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(in.nextInt());
        for (int i = 0; i < heap.arr.length - 1; ++i) {
            if (in.nextInt() == 0) {
                heap.insert(in.nextInt());
            } else {
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
