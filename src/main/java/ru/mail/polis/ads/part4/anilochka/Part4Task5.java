package ru.mail.polis.ads.part4.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part4Task5 {
    private Part4Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean isDecreasing = true;
        a[0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] > a[i - 1]) {
                isDecreasing = false;
            }
        }
        if (isDecreasing) {
            out.println(n * (n - 1) / 2);
            return;
        }
        out.println(countInv(a, 0, n));
    }

    private static int countInv(int[] a, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = (i + j) / 2;
        return countInv(a, i, mid) +
                countInv(a, mid, j) +
                countSplitInv(a, i, j, mid);
    }

    private static int countSplitInv(int[] a, int i, int j, int mid) {
        int ptr1 = i;
        int ptr2 = mid;
        int invCount = 0;
        int[] tmp = new int[j - i];
        int iter = 0;
        while (ptr1 < mid && ptr2 < j) {
            if (a[ptr1] <= a[ptr2]) {
                tmp[iter++] = a[ptr1++];
                continue;
            }
            tmp[iter++] = a[ptr2++];
            invCount += mid - ptr1;
        }
        while (ptr1 < mid) {
            tmp[iter++] = a[ptr1++];
        }
        while (ptr2 < j) {
            tmp[iter++] = a[ptr2++];
        }
        for (int k = i; k < j; k++) {
            a[k] = tmp[k - i];
        }
        return invCount;
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
