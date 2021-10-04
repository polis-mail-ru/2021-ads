package ru.mail.polis.ads.part2.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class ThirdTask {

    private static final int MAX = 10000000;

    private ThirdTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        long ai;
        long bi;
        long ci = 0;
        for (int i = 1, j = 1, k = 0; k < n; k++) {
            ai = (long)Math.pow(i, 2);
            bi = (long)Math.pow(j, 3);
            if (ai > bi) {
                ci = bi;
                j++;
                continue;
            }
            ci = ai;
            i++;
            if (ai == bi) {
                j++;
            }
        }
        out.println(ci);
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

