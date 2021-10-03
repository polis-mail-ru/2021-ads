package ru.mail.polis.ads.ponomarev.stepan.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int amount = in.nextInt();

        int cubeIndex = 1, squareIndex = 1;
        long square = 1, cube = 1;

        for (int i = 0; i < amount; i++) {
            square = (long) Math.pow(squareIndex, 2);
            cube = (long) Math.pow(cubeIndex, 3);

            if (square >= cube) {
                cubeIndex++;
            }

            if (cube >= square) {
                squareIndex++;
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