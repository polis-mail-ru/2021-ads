package ru.mail.polis.ads.part2.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task2 {

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
        int N = scanner.nextInt();
        int[] counter = new int[214];
        int first = scanner.nextInt();

        Arrays.fill(counter, 0);
        counter[106]++;

        for (int i = 1; i < N; i++) {
            counter[scanner.nextInt() - first + 106]++;
        }

        for (int i = 1; i < counter.length; i++) {
            if (counter[i] != 0) {
                int result = i + first - 106;
                for (int j = 0; j < counter[i]; j++) {
                    System.out.print(result + " ");
                }
            }
        }
    }
}

