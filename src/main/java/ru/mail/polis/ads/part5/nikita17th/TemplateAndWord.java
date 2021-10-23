package ru.mail.polis.ads.part5.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class TemplateAndWord {
    private TemplateAndWord() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String firstInput = in.next();
        char[] str;
        char[] template;
        if (firstInput.indexOf('*') != -1 || firstInput.indexOf('?') != -1) {
            str = in.next().toCharArray();
            template = firstInput.toCharArray();
        } else {
            str = firstInput.toCharArray();
            template = in.next().toCharArray();
        }

        int n = template.length;
        int m = str.length;
        boolean[][] d = new boolean[n + 1][m + 1];
        d[0][0] = true;

        char templateChar;
        for (int i = 1; i <= n; i++) {
            templateChar = template[i - 1];
            for (int j = 1; j <= m; j++) {
                d[i][j] = templateChar == '*' ? d[i - 1][j] || d[i][j - 1] || d[i - 1][j - 1]
                        : (templateChar == '?' || templateChar == str[j - 1]) && d[i - 1][j - 1];

            }

        }

        out.println(d[n][m] ? "YES" : "NO");

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
