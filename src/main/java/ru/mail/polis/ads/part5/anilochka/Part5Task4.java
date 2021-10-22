package ru.mail.polis.ads.part5.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part5Task4 {
    private Part5Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        boolean condition = s1.contains("*") || s1.contains("?");
        String[] s = new String[]{condition ? s1 : s2, condition ? s2 : s1};
        int[][] d = new int[s[0].length()][s[1].length()];
        for (int i = 0; i < s[0].length(); i++) {
            for (int j = 0; j < s[1].length(); j++) {
                if (s[0].charAt(i) == s[1].charAt(j)) {
                    d[i][j] = i > 0 && j > 0 ? d[i - 1][j - 1] : 1;
                    continue;
                }
                if (s[0].charAt(i) == '*') {
                    d[i][j] = i > 0 && j > 0 ? Math.max(d[i][j - 1], d[i - 1][j])
                            : (i > 0 ? d[i - 1][j] : (j > 0 ? d[i][j - 1] : 1));
                    continue;
                }
                if (s[0].charAt(i) == '?') {
                    d[i][j] = i > 0 && j > 0 ? d[i - 1][j - 1] : 1;
                }
            }
        }
        out.println(d[s[0].length() - 1][s[1].length() - 1] == 1 ? "YES" : "NO");
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
