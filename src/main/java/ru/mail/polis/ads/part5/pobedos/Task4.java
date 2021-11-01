package ru.mail.polis.ads.part5.pobedos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String first = in.next();
        String second = in.next();
        char[] template = null;
        char[] string = null;
        Set<Character> specialSymbols = new HashSet<>();
        specialSymbols.add('*');
        specialSymbols.add('?');
        char[] check = first.toCharArray();
        for (char c : check) {
            if (specialSymbols.contains(c)) {
                template = check;
                break;
            }
        }
        if (template == null) {
            template = second.toCharArray();
            string = check;
        } else {
            string = second.toCharArray();
        }
        boolean[][] matrix = new boolean[template.length][string.length];
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < string.length; j++) {
                if (template[i] == '*') {
                    matrix[i][j] = ((i == 0) && (j == 0)) || ((i != 0) && matrix[i - 1][j]) || ((j != 0) && matrix[i][j - 1]);
                } else if ((template[i] == '?') || (template[i] == string[j])) {
                    matrix[i][j] = ((i == 0) || (j == 0)) || matrix[i - 1][j - 1];
                }
            }
        }
        out.println(matrix[template.length - 1][string.length - 1] ? "YES" : "NO");
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
