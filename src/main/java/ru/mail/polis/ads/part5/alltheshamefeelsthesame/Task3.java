package ru.mail.polis.ads.part5.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task3 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int[] sequence = new int[N];
        int[] dynamics = new int[N];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = scanner.nextInt();
        }
        getResult(sequence, dynamics);
    }

    private static void getResult(int[] sequence, int[] dynamics) {
        Arrays.fill(dynamics, 1);
        int max;
        for (int i = 1; i < dynamics.length; i++) {
            max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (dynamics[j] > max && sequence[j] != 0 && sequence[i] % sequence[j] == 0) {
                    max = dynamics[j];
                }
            }
            dynamics[i] += max;
        }

        System.out.println(IntStream.of(dynamics).max().orElse(1));
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
