package ru.mail.polis.ads.part4.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9565173
public final class Week4Test5 {
    private Week4Test5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] input = new int[in.nextInt()];
        for (int i = 0; i < input.length; ++i) {
            input[i] = in.nextInt();
        }
        out.println(count(input, new int[input.length], 0, input.length - 1));
    }

    static int count(int[] array, int[] tempArray, int start, int end) {
        int count = 0;
        if (1 + start > end) {
            return count;
        }

        int mid = start + ((end - start) >> 1);
        count = count(array, tempArray, start, mid) + count(array, tempArray, mid + 1, end);

        System.arraycopy(array, start, tempArray, start, end - start + 1);

        int leftBound = mid + 1;
        int rightBound = end + 1;
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i < leftBound && j < rightBound) {
            if (tempArray[i] <= tempArray[j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
                count += leftBound - i;
            }
            k++;
        }

        while (i < leftBound) {
            array[k] = tempArray[i];
            i++;
            k++;
        }
        while (j < rightBound) {
            array[k] = tempArray[j];
            j++;
            k++;
            count += leftBound - i;
        }

        return count;
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
