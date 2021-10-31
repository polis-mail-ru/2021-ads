package ru.mail.polis.ads.part5.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    static private final double EPS = 1e-6;

    private static double func(double value) {
        return value * value + Math.sqrt(value);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double C = in.nextDouble();
        double left = 0;
        double right = Math.sqrt(C);
        while (Math.abs(func(left) - C) > EPS) {
            double middle = (left + right) / 2;
            double funcValue = func(middle);
            if (funcValue > C) {
                right = middle;
            } else {
                left = middle;
            }
        }
        System.out.println(left);
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

        double nextDouble() {
            return Double.parseDouble(next());
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