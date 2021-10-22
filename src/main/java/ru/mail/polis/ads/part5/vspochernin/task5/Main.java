package ru.mail.polis.ads.part5.vspochernin.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9616653
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        out.println(showArray(a));

        for (int k = 0; k < factorial(n) - 1; k++) {
            int maxI = -1;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] < a[i + 1]) {
                    maxI = i;
                }
            }
            int maxJ = -1;
            for (int j = maxI + 1; j < n; j++) {
                if (a[maxI] < a[j]) {
                    maxJ = j;
                }
            }
            swap(a, maxI, maxJ);
            reverse(a, maxI + 1, n - 1);
            out.println(showArray(a));
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void reverse(int[] a, int first, int last) {
        int length = last - first + 1;
        for (int i = 0; i < length / 2; i++) {
            int temp = a[first + i];
            a[first + i] = a[first + length - 1 - i];
            a[first + length - 1 - i] = temp;
        }
    }

    private static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static String showArray(int[] a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length - 1; i++) {
            result.append(a[i])
                    .append(" ");
        }
        result.append(a[a.length - 1]);
        return result.toString();
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
