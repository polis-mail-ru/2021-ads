package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private static int[][] d;
    private static int[][] bestDeterminer;
    private static String input;

    private TaskA() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        input = in.nextLine();
        final int n = input.length();

        if (input.equals("")) {
            System.out.println("");
        }
        d = new int[n][n];
        bestDeterminer = new int[n][n];
        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                int j = i + k;
                int minVal = d[i][i] + d[i + 1][j];
                bestDeterminer[i][j] = i;
                if (input.charAt(i) == '(' && input.charAt(j) == ')'
                        || input.charAt(i) == '[' && input.charAt(j) == ']') {
                    minVal = d[i + 1][j - 1];
                    bestDeterminer[i][j] = -1;
                }
                for (int l = i; l < j; l++) {
                    int newVal = (d[i][l] + d[l + 1][j]);
                    if (minVal > newVal) {
                        minVal = newVal;
                        bestDeterminer[i][j] = l;
                    }
                }
                d[i][j] = minVal;
            }
        }
        System.out.println(solveBrackets(0, n - 1));
    }

    private static String solveBrackets(int l, int r) {
        if (l > r) {
            return "";
        }
        if (l == r) {
            switch (input.charAt(l)) {
                case '(':
                case ')':
                    return "()";
                case '[':
                case ']':
                    return "[]";
            }
        }
        if(d[l][r] == 0) {
            return input.substring(l,r+1);
        }
        if (bestDeterminer[l][r] == -1) {
            return input.charAt(l) + solveBrackets(l+1, r-1) + input.charAt(r);
        }
        return solveBrackets(l, bestDeterminer[l][r]) + solveBrackets(bestDeterminer[l][r] + 1, r);
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

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
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
