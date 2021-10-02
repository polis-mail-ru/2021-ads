package ru.mail.polis.ads.part2.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task3 {

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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int x = scanner.nextInt() - 1;
        int currentA = 2;
        int currentB = 2;
        long result = 1;
        long A;
        long B;

        for (int i = 0; i < x; i++) {
            A = (long) currentA * currentA;
            B = (long) currentB * currentB * currentB;
            if (A == B) {
                result = A;
                currentA++;
                currentB++;
                continue;
            }
            if (A > B) {
                currentB++;
            } else {
                currentA++;
            }
            result = Long.min(A, B);
        }
        System.out.println(result);
    }
}
