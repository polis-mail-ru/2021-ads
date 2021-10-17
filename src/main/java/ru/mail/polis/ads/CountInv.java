package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountInv {
    //https://www.e-olymp.com/ru/submissions/9567074
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(countInv(a, 0, n));
    }

    public static int countInv(int[] a, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = (j + i) / 2;
        return countInv(a, i, mid) + countInv(a, mid, j) + countSplitInv(a, i, j, mid);

    }

    private static int countSplitInv(int[] a, int fromInclusive, int toExclusive, int mid) {
        int countInv = 0;
        int index1 = fromInclusive;
        int index2 = mid;
        int[] result = new int[toExclusive - fromInclusive];
        for (int i = 0; i < toExclusive - fromInclusive; i++) {
            if (index1 == mid) {
                result[i] = a[index2];
                index2++;
            } else if (index2 == toExclusive || a[index1] <= a[index2]) {
                result[i] = a[index1];
                index1++;
            } else {
                result[i] = a[index2];
                countInv += mid - index1;
                index2++;
            }
        }
        if (toExclusive - fromInclusive >= 0)
            System.arraycopy(result, fromInclusive - fromInclusive, a, fromInclusive, toExclusive - fromInclusive);
        return countInv;
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
