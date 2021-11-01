package ru.mail.polis.ads.part5.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            out.print(arr[i] + " ");
        }
        out.println();
        for (int i = 0; i < factorial(n) - 1; i++) {
            int maxJ = 0;
            int maxL = 0;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j + 1] > arr[j]) {
                    maxJ = j;
                }
            }
            for (int j = 0; j < n; j++) {
                if (arr[j] > arr[maxJ]) {
                    maxL = j;
                }
            }
            swap(arr, maxJ, maxL);
            for (int j = 0; j < maxJ + 1; j++) {
                out.print(arr[j] + " ");
            }
            int mid = (n - 1 - (maxJ + 1)) / 2;
            for (int j = maxJ + 1; j < n; j++) {
                if (n - 1 - j > mid) {
                    swap(arr, j, n - j + maxJ);
                }
                out.print(arr[j] + " ");
            }
            out.println();
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
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
