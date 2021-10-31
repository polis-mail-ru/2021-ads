package ru.mail.polis.ads.part5.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        char[] pattern;
        char[] line;
        String firstString = in.next();
        String secondString = in.next();
        if (firstString.contains("?") || firstString.contains("*")) {
            pattern = firstString.toCharArray();
            line = secondString.toCharArray();
        } else {
            line = firstString.toCharArray();
            pattern = secondString.toCharArray();
        }
        final int n = pattern.length;
        final int m = line.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                final int minusI = i - 1;
                final int minusJ = j - 1;
                if (pattern[minusI] == line[minusJ] || pattern[minusI] == '?') {
                    dp[i][j] = dp[minusI][minusJ];
                } else if (pattern[minusI] == '*') {
                    dp[i][j] = dp[minusI][j] || dp[i][minusJ] || dp[minusI][minusJ];
                }
            }
        }
        out.println(dp[n][m] ? "YES" : "NO");
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
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