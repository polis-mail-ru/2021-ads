package ru.mail.polis.ads.part5.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task1 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        double C = Double.parseDouble(scanner.next());
        System.out.println(findX(C));
    }

    private static double findX(double C) {
        double left = 0;
        double right = Math.sqrt(C);
        double mid = (left + right) / 2;
        double expression;

        while (true) {
            expression = mid * mid + Math.sqrt(mid);
            if (Math.abs(C - expression) <= 1e-6) {
                System.out.println(expression + " " + 1e6);
                return mid;
            }

            if (expression < C) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
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

