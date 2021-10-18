package ru.mail.polis.ads.part4.sorgeligt;


import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9576810
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        final int m = in.nextInt();
        int[] b = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = in.nextInt();
        }
        out.println(getMaxLengthSubsequence(n, a, m, b));
    }


    /**
     * Двумерная динамика использующая O(m) памяти
     */
    private static int getMaxLengthSubsequence(int n, int[] a, int m, int[] b) {
        int[] dp = new int[m + 1];
        int upperLeftElement = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    final int tmp = upperLeftElement;
                    upperLeftElement = dp[j];
                    dp[j] = tmp + 1;
                } else {
                    upperLeftElement = dp[j];
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
            }
            upperLeftElement = 0;
        }
        return dp[m];
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