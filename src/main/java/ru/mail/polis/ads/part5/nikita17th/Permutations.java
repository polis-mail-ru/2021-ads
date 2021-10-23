package ru.mail.polis.ads.part5.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class Permutations {
    private Permutations() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
        }

        int i;
        StringBuilder resStr;
        while (true) {
            resStr = new StringBuilder();
            for (int el : p) {
                resStr.append(el).append(" ");
            }
            out.println(resStr);

            for (i = n - 2; i >= 0; i--) {
                if (p[i] < p[i + 1]) {
                    break;
                }
            }

            if (i == -1) {
                break;
            }

            int ceilIndex = i + 1;
            int firstElement = p[i];
            for (int j = ceilIndex + 1; j < n; j++) {
                if (p[j] > firstElement && p[j] < p[ceilIndex]) {
                    ceilIndex = j;
                }
            }
            swap(p, i, ceilIndex);

            for (int left = i + 1, right = n - 1; left < right; left++, right--) {
                swap(p, left, right);
            }

        }
    }

    private static void swap(int[] a, int iFirst, int iSecond) {
        int tmp = a[iFirst];
        a[iFirst] = a[iSecond];
        a[iSecond] = tmp;
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
