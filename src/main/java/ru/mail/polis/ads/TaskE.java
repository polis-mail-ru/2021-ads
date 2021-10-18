package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    static int[] buffer;
    private TaskE() {
        // Should not be instantiated
    }

    private static int countInversionAmount(int[] array, int startPos, int endPos) {
        if (endPos - startPos <=1) {
            return 0;
        }

        int mid = startPos + (endPos - startPos) / 2;

        return countInversionAmount(array, startPos, mid)
                + countInversionAmount(array, mid, endPos)
                + merge(array, startPos, mid, endPos);
    }

    private static int merge(int[] array, int startPos, int mid, int endPos) {
        int i = 0;
        int j = 0;
        int invCount = 0;
        while (startPos + i < mid && mid + j < endPos) {
            if (array[startPos + i] <= array[mid + j]) {
                buffer[i + j] = array[startPos + i];
                i++;
            } else {
                buffer[i + j] = array[mid + j];
                j++;
                invCount += mid - (startPos + i);
            }
        }
        while (startPos + i < mid) {
            buffer[i + j] = array[startPos + i];
            i++;
        }
        while (mid + j < endPos) {
            buffer[i + j] = array[mid + j];
            j++;
        }

        for (i = 0; i < endPos - startPos; i++) {
            array[startPos + i] = buffer[i];
        }
        return invCount;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] array = new int[n];
        buffer = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.print(countInversionAmount(array, 0, array.length));
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
