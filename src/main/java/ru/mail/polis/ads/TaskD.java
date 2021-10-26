package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str1 = in.next();
        String str2 = in.next();
        char[] str;
        char[] template;
        if (str1.contains("?") || str1.contains("*")) {
            template = str1.toCharArray();
            str = str2.toCharArray();
        } else {
            template = str2.toCharArray();
            str = str1.toCharArray();
        }
        boolean[][] d = new boolean[template.length][str.length];
        if (template[0] == '*' || template[0] == '?') {
            for (int i = 0; i < str.length; i++) {
                d[0][i] = true;
            }
        } else if (template[0] != str[0]) {
            out.println("NO");
            return;
        }
        d[0][0] = true;
        for (int i = 1; i < template.length; i++) {
            if(str[0] == template[i] || template[i] == '?') {
                d[i][0] = true;
            }
            if (template[i] == '*') {
                d[i][0] = d[i - 1][0];
            }
        }
        for (int i = 1; i < template.length; i++) {
            for (int j = 1; j < str.length; j++) {
                if (str[j] == template[i] || template[i] == '?') {
                    d[i][j] = d[i - 1][j - 1];
                }
                if (template[i] == '*') {
                    d[i][j] = d[i][j - 1] || d[i - 1][j];
                }
            }
        }
        out.println(d[template.length - 1][str.length - 1] ? "YES" : "NO");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
