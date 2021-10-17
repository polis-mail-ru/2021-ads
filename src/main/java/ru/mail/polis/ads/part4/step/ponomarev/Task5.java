package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9554540

public class Task5 {
    static int[] helperArray;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int size = in.nextInt();
        helperArray = new int[size];

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }

        int inversionCount = countInversionAmount(array, 0, array.length - 1);
        out.print(inversionCount);
    }

    private static int countInversionAmount(int[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return 0;
        }

        int mid = startPos + (endPos - startPos) / 2;

        return countInversionAmount(array, startPos, mid)
                + countInversionAmount(array, mid + 1, endPos)
                + merge(array, startPos, mid, endPos);
    }

    private static int merge(int[] arr, int start, int mid, int end) {
        int l = start;
        int r = mid + 1;
        int inversionAmount = 0;

        for (int i = start; i <= end; i++) {
            helperArray[i] = arr[i];
        }

        for (int i = start; i <= end; i++) {
            if (l > mid) {
                arr[i] = helperArray[r++];
                continue;
            }

            if (r > end) {
                arr[i] = helperArray[l++];
                continue;
            }

            if (helperArray[l] < helperArray[r]) {
                arr[i] = helperArray[l++];
            } else {
                inversionAmount += mid + 1 - l;
                arr[i] = helperArray[r++];
            }
        }

        return inversionAmount;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
