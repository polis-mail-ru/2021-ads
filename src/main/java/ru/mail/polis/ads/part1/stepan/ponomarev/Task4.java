package ru.mail.polis.ads.part1.stepan.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String seq = in.next();
        if (seq == null || seq.isEmpty()) {
            out.println("yes");
            return;
        }

        out.println(seq);
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

    private enum Bracket {
        OPEN(new char[]{'(', '['}),
        CLOSE(new char[]{')', ']'});

        private final char[] bracket;

        Bracket(char[] bracket) {
            this.bracket = bracket;
        }

        public static Bracket of(char ch) {
            for (Bracket bracket : Bracket.values()) {
                for (char br : bracket.bracket) {
                    if (Objects.equals(br, ch)) {
                        return bracket;
                    }
                }
            }

            throw new IllegalArgumentException("Unsupported char: " + ch);
        }
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
}
