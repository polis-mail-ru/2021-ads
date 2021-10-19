package ru.mail.polis.ads.part4.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task2 {

    private static int M;
    private static int N;

    private static void findWay(int[][] map) {
        for (int  i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (j - 1 >= 0 && i - 1 >= 0) {
                    map[i][j] += Math.max(map[i - 1][j], map[i][j - 1]);
                } else if (j - 1 >= 0) {
                    map[i][j] += map[i][j - 1];
                } else if (i - 1 >= 0) {
                    map[i][j] += map[i - 1][j];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        int i = M - 1;
        int j = N - 1;
        while (i != 0 || j != 0) {
            if (j - 1 >= 0 && i - 1 >= 0) {
                if (map[i - 1][j] > map[i][j - 1]) {
                    answer.append("F");
                    i--;
                } else {
                    answer.append("R");
                    j--;
                }
            } else if (j - 1 >= 0) {
                answer.append("R");
                j--;
            } else if (i - 1 >= 0) {
                answer.append("F");
                i--;
            }
        }
        System.out.println(answer.reverse());
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

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        M = in.nextInt();
        N = in.nextInt();
        int[][] map = new int[M][N];

        for (int i = M - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                map[i][j] = in.nextInt();
            }
        }
        findWay(map);
    }
}
