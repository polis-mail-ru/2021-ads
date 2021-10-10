package ru.mail.polis.ads.ponomarev.stepan.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9496437
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];

        for (int i = 0; i < data.length; ++i) {
            data[i] = in.nextInt();
        }

        sort(data);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            str.append(data[i]);

            if (i != data.length - 1) {
                str.append(" ");
            }
        }

        out.println(str.toString());
    }

    static void sort(final int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 0; --k) {
            sink(array, k, n);
        }

        while (n > 0) {
            swap(array, 0, n--);
            sink(array, 0, n);
        }
    }

    static void sink(final int[] array, int index, final int length) {
        int pos = 2 * index + 1;
        while (pos <= length) {
            if (pos < length && array[pos] < array[pos + 1]) {
                ++pos;
            }

            if (array[index] >= array[pos]) {
                break;
            }

            swap(array, index, pos);
            index = pos;
            pos = 2 * index + 1;
        }
    }

    static void swap(final int[] array, final int firstIndex, final int secondIndex) {
        if (array[firstIndex] == array[secondIndex]) {
            return;
        }

        int tmp = array[firstIndex];
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