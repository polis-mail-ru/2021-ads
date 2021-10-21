package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part4Task5 {

    private static int count = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        int[] buffer = new int[n];

        for(int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        sort(array, buffer, 0, n - 1);
        out.println(count);
    }

    private static void sort(int[] array, int[] buffer, int start, int end) {
        if (start < end) {
            int mid = start + ((end - start) >> 1);
            sort(array, buffer, start, mid);
            sort(array, buffer, mid + 1, end);
            merge(array, buffer, start, mid, end);
        }
    }

    private static void merge(int[] array, int[] buffer, int start, int mid, int end) {
        System.arraycopy(array, start, buffer, start, end - start + 1);
        int left = start;
        int right = mid + 1;
        for (int i = start; i <= end; ++i) {
            if (left > mid) {
                array[i] = buffer[right++];
            } else if (right > end) {
                array[i] = buffer[left++];
            } else if (buffer[left] <= buffer[right]) {
                array[i] = buffer[left++];
            } else {
                array[i] = buffer[right++];
                count += mid + 1 - left;
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
