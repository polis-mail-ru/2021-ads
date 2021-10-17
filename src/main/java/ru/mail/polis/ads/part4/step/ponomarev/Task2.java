package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9561028

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int m = in.nextInt();
        final int n = in.nextInt();

        final int[][] board = new int[m][n];
        final int[][] costStory = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = in.nextInt();
            }
        }

        final int finishM = 0;
        final int finishN = n - 1;
        
        out.print(getPath("", m - 1,  finishM, finishN, board, costStory));
    }

    private static String getPath(String path, final int maxM, final int m, final int n, final int[][] board, final int[][] costStory) {        
        int costFromDown = getCost(maxM, m + 1, n, board, costStory);
        int costFromLeft = getCost(maxM, m, n - 1, board, costStory);
        
        if (m == maxM && n == 0) {
            return path;
        }

        path = (costFromDown > costFromLeft ? "F" : "R") + path;
        int nextM = costFromDown > costFromLeft ? m + 1 : m;
        int nextN = costFromDown > costFromLeft ? n : n - 1;

        return getPath(path, maxM, nextM, nextN, board, costStory);
    }

    private static int getCost(final int maxM, final int m, final int n, final int[][] board, final int[][] costStory) {
        if (m == maxM && n == 0) {
            return 0;
        }

        if (m > maxM || n < 0) {
            return -1;
        }

        int costFromDown = -1;
        if (m < maxM) {
            costFromDown = costStory[m + 1][n] == 0 ? getCost(maxM, m + 1, n, board, costStory) : costStory[m + 1][n];
        }

        int costFromLeft = -1;
        if (n > 0) {
            costFromLeft = costStory[m][n - 1] == 0 ? getCost(maxM, m, n - 1, board, costStory) : costStory[m][n - 1];
        }

        int newCostStory = Math.max(costFromDown, costFromLeft) + board[m][n];
        if (newCostStory > costStory[m][n]) {
            costStory[m][n] = newCostStory;
        }

        return costStory[m][n];
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
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
