package ru.mail.polis.ads.part5.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 * 991. Шаблон и слово
 * <p>
 * https://www.e-olymp.com/ru/submissions/9644612
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final String a = in.next();
        final String b = in.next();
        out.println((isWord(a) ? new Template(b).test(a) : new Template(a).test(b)) ? "YES" : "NO");
    }

    private static class Template implements Predicate<String> {
        public static final char ANY_STRING = '*';
        public static final char ANY_CHAR = '?';

        private final char[] template;
        private final boolean[][] subMatches;
        private char[] word;

        private Template(char[] template) {
            this.template = template;
            subMatches = new boolean[template.length][];
        }

        public Template(String template) {
            this(template.toCharArray());
        }

        @Override
        public boolean test(String s) {
            this.word = s.toCharArray();
            Arrays.setAll(subMatches, i -> new boolean[word.length]);
            for (int i = 0; i < template.length; i++) {
                for (int j = 0; j < word.length; j++) {
                    subMatches[i][j] = subMatch(i, j, template[i]);
                }
            }
            return subMatches[template.length - 1][word.length - 1];
        }

        private boolean subMatch(int i, int j, char templateChar) {
            switch (templateChar) {
                case ANY_STRING:
                    return i == 0 || subMatches[i - 1][j] || j != 0 && subMatches[i][j - 1];
                case ANY_CHAR:
                    return i == 0 && j == 0 || i != 0 && j != 0 && subMatches[i - 1][j - 1];
                default:
                    return word[j] == templateChar && subMatch(i, j, ANY_CHAR);
            }
        }
    }

    private static boolean isWord(String s) {
        return s.chars().allMatch(Character::isLetter);
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
