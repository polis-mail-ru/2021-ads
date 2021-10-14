package ru.mail.polis.ads.part3.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9543409
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        for (int i = 1; i <= n; i++) {
            out.print(array[i] + " ");
        }
    }

    private static void heapSort(int[] array) {
        int n = array.length - 1;
        for (int i = n / 2; i >= 1; i--) {
            sink(array, i, n);
        }
        for (int i = 0; i < array.length - 2; i++) {
            swap(array, 1, n);
            sink(array, 1, --n);
        }
    }

    private static void sink(int[] heap, int k, int topElem) {
        while (2 * k <= topElem) {
            int j = 2 * k;
            if (j < topElem && heap[j + 1] > heap[j]) {
                j++;
            }
            if (heap[k] >= heap[j]) {
                break;
            }
            swap(heap, k, j);
            k = j;
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        final int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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