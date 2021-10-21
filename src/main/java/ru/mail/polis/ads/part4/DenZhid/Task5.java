package ru.mail.polis.ads.part4.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task5 {

    private static int countInv(int[] in, int[] arrayForMerge, int left, int right) {
        if (left == right - 1) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return countInv(in, arrayForMerge, left, mid)
                + countInv(in, arrayForMerge, mid, right)
                + countSplitInv(in, arrayForMerge, left, mid, right);
    }

    private static int countSplitInv(int[] in, int[] arrayForMerge, int left, int mid, int right) {
        int invCounter = 0;
        int firstPointer = left;
        int secondPointer = mid;
        if (right - left >= 0) System.arraycopy(in, left, arrayForMerge, left, right - left);
        for (int i = left; i < right; i++) {
            if (firstPointer ==  mid) {
                in[i] = arrayForMerge[secondPointer++];
            } else if (secondPointer == right) {
                in[i] = arrayForMerge[firstPointer++];
            } else if (arrayForMerge[firstPointer] < arrayForMerge[secondPointer]) {
                in[i] = arrayForMerge[firstPointer++];
            } else {
                in[i] = arrayForMerge[secondPointer++];
                invCounter += mid - firstPointer;
            }
        }
        return invCounter;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = in.nextInt();
        }
        int[] tmp = new int[size];
        out.println(countInv(a, tmp, 0, size));
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
