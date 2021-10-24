package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class FindRadicalTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        final double EPS = 1e-6;
        double c = Double.parseDouble(in.next());
        double left = 0;
        double right = c;
        double mid = (right + left) / 2.0;
        double fx = fun(mid,c);
        while (Math.abs(fx) > EPS) {
            if (fx < 0) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (right + left) / 2;
            fx = fun(mid,c);
        }
        out.println(mid);
    }

    private static double fun(double x, double c){
        return x*x + Math.sqrt(x) - c;
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
