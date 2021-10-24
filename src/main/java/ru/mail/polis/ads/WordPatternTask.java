package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class WordPatternTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String data = in.next();
        char[] dataChars;
        char[] patternChars;
        if (data.indexOf('*') != -1 || data.indexOf('?') != -1) {
            patternChars = data.toCharArray();
            dataChars = in.next().toCharArray();
        } else {
            patternChars = in.next().toCharArray();
            dataChars = data.toCharArray();
        }
        int n = patternChars.length;
        int m = dataChars.length;

        boolean[][] dynamic = new boolean[n + 1][m + 1];
        dynamic[0][0] = true;
        char patternChar;
        for (int i = 1; i <= n; i++) {
            patternChar = patternChars[i - 1];
            for (int j = 1; j <= m; j++) {
                if (patternChar == '*') {
                    dynamic[i][j] = dynamic[i][j - 1] || dynamic[i - 1][j] || dynamic[i - 1][j - 1];
                    continue;
                }
                if (patternChar == '?' || patternChar == dataChars[j - 1]) {
                    dynamic[i][j] = dynamic[i - 1][j - 1];
                }
            }
        }
        out.println(dynamic[n][m] ? "YES" : "NO");
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
