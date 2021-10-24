package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class DiplomaTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();

        long right = (long) Math.max(w, h) * n;
        long left = 0;
        long mid = (right + left) >> 1;
        boolean fx = fun(mid, w, h, n);
        while (left < right) {
            if (fx) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = (right + left) >> 1;
            fx = fun(mid, w, h, n);
        }
        out.println(left);
    }

    private static boolean fun(long x, int w, int h, int n) {
        return (x / w) * (x / h) >= n;
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
