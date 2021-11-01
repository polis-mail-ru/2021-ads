package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part5Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String firstString = in.next();
        String secondString = in.next();

        String word;
        String pattern;

        if (firstString.contains("?") || firstString.contains("*")) {
            word = secondString;
            pattern = firstString;
        } else {
            word = firstString;
            pattern = secondString;
        }

        boolean[][] d = new boolean[pattern.length()+ 1][word.length() + 1];
        d[0][0] = true;
        for (int i = 1; i <= pattern.length(); ++i) {
            for (int j = 1; j <= word.length(); ++j) {
                switch (pattern.toCharArray()[i - 1]) {
                    case '?': {
                        d[i][j] = d[i - 1][j - 1];
                        break;
                    }
                    case '*': {
                        if (d[i][j - 1] || d[i - 1][j] || d[i - 1][j - 1]) {
                            d[i][j] = true;
                        }
                        break;
                    }
                }
                if (pattern.toCharArray()[i - 1] == word.toCharArray()[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];
                }
            }
        }
        out.println(d[pattern.length()][word.length()] ? "YES" : "NO");

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
