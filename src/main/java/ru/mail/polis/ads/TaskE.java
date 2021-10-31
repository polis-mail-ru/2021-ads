package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int arrSize = in.nextInt();
        int[] array = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            array[i] = i + 1;
        }
        while (true) {
            for (int i = 0; i < arrSize; i++) {
                out.print(array[i] + " ");
            }
            out.println();
            // Index Of First Growing Pair
            int iGP;
            for (iGP = arrSize - 2; iGP >= 0; iGP--) {
                if (array[iGP + 1] > array[iGP]) {
                    break;
                }
            }
            if (iGP == -1) {
                return;
            }
            // Swap iGP with first element, bigger than iGP
            for (int k = arrSize - 1; k > iGP; k--) {
                if (array[k] > array[iGP]) {
                    swap(array, k, iGP);
                    break;
                }
            }
            // reverse
            for (int i1 = iGP + 1, i2 = arrSize - 1; i1 < i2; i1++, i2--) {
                swap(array, i1, i2);
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
