package ru.mail.polis.ads.part5.vspochernin.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9619915
 */
public final class Main {
    private Main() {
// Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        String template = in.next();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?' || str.charAt(i) == '*') {
                String temp = template;
                template = str;
                str = temp;
                break;
            }
        }
        boolean[][] d = new boolean[template.length()][str.length()];

        for (int i = 0; i < template.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (template.charAt(i) == '*') {
                    if (i == 0 && j == 0) {
                        d[i][j] = true;
                    } else {
                        d[i][j] = (j > 0) ? d[i][j - 1] : d[i][j];
                        d[i][j] = (i > 0) ? (d[i][j] || d[i - 1][j]) : d[i][j];
                    }
                } else if (template.charAt(i) == '?' || str.charAt(j) == template.charAt(i)) {
                    if (i == 0 && j == 0) {
                        d[i][j] = true;
                    } else if (i > 0 && j > 0) {
                        d[i][j] = d[i - 1][j - 1];
                    } else {
                        d[i][j] = false;
                    }
                } else {
                    d[i][j] = false;
                }
            }
        }
        out.println(d[template.length() - 1][str.length() - 1] ? "YES" : "NO");
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