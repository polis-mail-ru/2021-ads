package ru.mail.polis.ads.ponomarev.stepan.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9495379

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[] array = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            array[i] = in.nextInt();
        }

        out.println(isHeap(array) ? "YES" : "NO");
    }

    private static boolean isHeap(final int[] array) {
        int childIndex;

        for (int i = 1; i < array.length; i++) {
            childIndex = i * 2;
            if (childIndex < array.length && array[i] > array[childIndex]) {
                return false;
            }

            childIndex++;
            if (childIndex < array.length && array[i] > array[childIndex]) {
                return false;
            }
        }

        return true;
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