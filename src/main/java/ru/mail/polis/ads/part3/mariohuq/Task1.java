package ru.mail.polis.ads.part3.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 3737. Куча ли?
 * <p>
 * https://www.e-olymp.com/ru/submissions/9513104
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private final static int FIRST_CHILD_INDEX = 2;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] inputs = new int[in.nextInt() + 1];
        for (int i = 1; i < inputs.length; i++) {
            inputs[i] = in.nextInt();
        }
        out.println(isHeap(inputs) ? "YES" : "NO");
    }

    private static boolean isHeap(int[] array) {
        for (int i = FIRST_CHILD_INDEX; i < array.length; i++) {
            if (!heapInvariantHolds(array, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean heapInvariantHolds(int[] heap, int child_index) {
        return heap[child_index / 2] <= heap[child_index];
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