package ru.mail.polis.ads.part5.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2169. Перестановки
 * <p>
 * https://www.e-olymp.com/ru/submissions/9645107
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] permutation = new int[in.nextInt()];
        Arrays.setAll(permutation, i -> i + 1);
        do {
            println(out, permutation, " ");
        } while (next(permutation));
    }

    private static void println(final PrintWriter out, int[] array, String separator) {
        if (0 < array.length) {
            out.print(array[0]);
        }
        for (int i = 1; i < array.length; i++) {
            out.print(separator);
            out.print(array[i]);
        }
        out.println();
    }

    private static boolean next(int[] permutation) {
        int n = permutation.length;
        for (int i = n - 2; i >= 0; i--) {
            if (permutation[i] < permutation[i + 1]) {
                int min = i + 1;
                for (int j = i + 2; j < n; j++){
                    if (permutation[j] < permutation[min] && permutation[j] > permutation[i]) {
                        min = j;
                    }
                }
                swap(permutation, i, min);
                reverse(permutation, i + 1, n);
                return true;
            }
        }
        return false;
    }

    private static void reverse(int[] array, int begin, int end) {
        for (int i = 0, middle = (end - begin) / 2; i < middle; i++) {
            swap(array, begin + i, end - 1 - i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
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
