package ru.mail.polis.ads.part4.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {

    public static int N;
    public static int K;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        N = scanner.nextInt();
        int[] stairs = new int[N + 1];
        for (int i = 0; i < N; i++) {
            stairs[i] = scanner.nextInt();
        }
        stairs[N] = 0;
        K = scanner.nextInt();

        System.out.println(sum(stairs));
    }

    private static int max(int[] stairs, int start) {
        int max = Integer.MIN_VALUE;
        for (int i = start - 1; i >= start - K; i--) {
            if (i < 0) {
                break;
            }
            max = Math.max(stairs[i], max);
        }
        return max;
    }

    private static int sum(int[] stairs) {
        int current;
        for (int i = 1; i < stairs.length; i++) {
            current = i < K ? Math.max(max(stairs, i), 0) : max(stairs, i);
            stairs[i] += current;
        }
        return stairs[stairs.length - 1];
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
