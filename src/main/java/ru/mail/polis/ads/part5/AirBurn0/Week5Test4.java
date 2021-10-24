package ru.mail.polis.ads.part5.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9639821
public final class Week5Test4 {
    private Week5Test4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String line = in.next();
        String temp = in.next();

        if (line.contains("?") || line.contains("*")) {
            String t = temp;
            temp = line;
            line = t;
        }

        boolean[][] d = new boolean[temp.length()][line.length()];
        for (int i = 0; i < temp.length(); ++i) {
            for (int j = 0; j < line.length(); ++j) {
                if (i > 0 || j > 0) {
                    if (temp.charAt(i) == '*') {
                        d[i][j] = d[i][j == 0 ? 0 : j - 1] || d[i == 0 ? 0 : i - 1][j];
                        continue;
                    }
                    if (i > 0 && j > 0 && (temp.charAt(i) == line.charAt(j) || temp.charAt(i) == '?')) {
                        d[i][j] = d[i - 1][j - 1];
                        continue;
                    }
                    d[i][j] = false;

                } else {
                    d[i][j] = temp.charAt(0) == line.charAt(0) || temp.charAt(0) == '?' || temp.charAt(0) == '*';
                }
            }
        }

        out.println(d[temp.length() - 1][line.length() - 1] ? "YES" : "NO");
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
