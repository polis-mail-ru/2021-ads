package ru.mail.polis.ads.part4.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task3 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] firstWord = new int[n];
        for (int i = 0; i < n; i++) {
            firstWord[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] secondWord = new int[m];
        for (int i = 0; i < m; i++) {
            secondWord[i] = scanner.nextInt();
        }
        int[][] table = new int[n + 1][m + 1];
        findMax(table, firstWord, secondWord);
    }

    private static void findMax(int[][] table, int[] first, int[] second) {
        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                table[i][j] = first[i - 1] == second[j - 1] ? table[i - 1][j - 1] + 1
                        : Math.max(table[i - 1][j], table[i][j - 1]);
            }
        }
        System.out.println(table[first.length][second.length]);
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
