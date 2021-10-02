package ru.mail.polis.ads.part2.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int x = in.nextInt();
        int i = 1;
        int j = 1;
        long square = i * i;
        long cube = j * j * j;
        for (int k = 0; k < x; k++) {
            square = (long) i * i;
            cube = (long) j * j * j;
            if (square >= cube) {
                j++;
            }
            if (cube >= square) {
                i++;
            }
        }
        System.out.println(Math.min(square, cube));
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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