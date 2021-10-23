package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.imageio.ImageTranscoder;

/**
 * Problem solution template.
 */
public final class Task4 {
    private static final double PRECISION = 6;

    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String a = in.next();
        String b = in.next();

        if (a.length() < b.length()) { //As it turned out, input data hasn't got strict order.
            //Therefore, we have to check whether a pattern is contained in a first or in a second line.
            String temp = a;
            a = b;
            b = a;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '*' || a.charAt(i) == '?') {
                String temp = a;
                a = b;
                b = a;
            }
        }

        boolean[][] d = new boolean[b.length()][a.length()];
        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                switch (b.charAt(i)) {
                    case '*':
                        if (((i != 0) && (d[i - 1][j])) || ((j != 0) && (d[i][j - 1])) || (i == 0)) {
                            d[i][j] = true;
                        }
                        break;
                    case '?':
                        if (((j > 0 && i > 0 && d[i - 1][j - 1]) || (i == 0))) {
                            d[i][j] = true;
                        }
                        break;
                    default:
                        if ((a.charAt(j) == b.charAt(i)) && ((j > 0 && i > 0 && d[i - 1][j - 1]) || (i == 0))) {
                            d[i][j] = true;
                        }
                        break;
                }
            }
        }
        for (int i = 0; i < a.length(); i++) {
            if (d[b.length() - 1][i]) {
                out.println("YES");
                return;
            }
        }
        out.println("NO");
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
