package ru.mail.polis.ads.part4.AirBurn0;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9637772
public final class Week4Test1 {
    private Week4Test1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String line = in.next();
        final int l = line.length();
        // generate triangle array
        int[][] t = new int[l][];
        for (int i = 0; i < l; ++i) {
            t[i] = new int[l - i];
        }

        int min = 0;
        int ascii = 0;
        for (int j = 0; j < l; ++j) {
            for (int i = 0; i + j < l; ++i) {
                if (j == 0) {
                    t[i][j] = 1;
                } else {
                    min = t[i][j - 1] + t[i + 1][j - 1];

                    // ASCII based hack, true for pair of ( ) and [ ], false if other
                    ascii = line.charAt(j + i) - line.charAt(i);
                    if (ascii > 0 && ascii < 3) {
                        min = Math.min(min, j > 1 ? t[i + 1][j - 2] : 0);
                    }

                    for (int k = 0; k < j; ++k) {
                        min = Math.min(t[i][k] + t[i + k + 1][j - k - 1], min);
                    }

                    t[i][j] = min;
                }
            }
        }
        out.println(buildResult(line, t, 0, l - 1));
    }

    private static String buildResult(String line, int[][] t, int i, int j) {
        if (i == j) {
            if (line.charAt(i) - '(' < 2) {
                return "()";
            }
            return "[]";
        }

        if (i < j) {
            int ascii = line.charAt(j) - line.charAt(i);

            if (ascii > 0 && ascii < 3 && t[i][j - i] == (j - i > 1 ? t[i + 1][j - i - 2] : 0)) {
                return line.charAt(i) + buildResult(line, t, i + 1, j - 1) + line.charAt(j);
            }

            for (int k = 0; k + i < j; ++k) {
                if (t[i][j - i] == t[i][k] + t[i + k + 1][j - i - k - 1]) {
                    return buildResult(line, t, i, i + k) + buildResult(line, t, i + k + 1, j);
                }
            }
        }
        return "";
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
                } catch (Exception e) {
                    return ""; // sorry for exception driven code
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
