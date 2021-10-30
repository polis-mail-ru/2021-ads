package ru.mail.polis.ads.part5.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9690905

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final double validDelta = 0.0000001;
        
        double expectedResult = Double.parseDouble(in.next());
        double min = 0;
        double max = expectedResult;
        
        double x = min + (max - min) / 2;
        double currResult = function(x);
        while (Math.abs(currResult - expectedResult) > validDelta) {
            currResult = function(x);

            //  Больше чем надо
            if (currResult - expectedResult > 0) {
                max = x;
            } else {
                min = x;
            }

            x = min + (max - min) / 2;
        }
        
         out.printf("%.6f", x);
    }

    private static double function(double x) {
        return Math.pow(x, 2) + Math.sqrt(x);
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
        
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
