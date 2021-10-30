package ru.mail.polis.ads.part5.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9691946

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        String template = in.next();

        if (str.equals(template)) {
            out.println("YES");
            return;
        }

        boolean[][] story = new boolean[template.length() + 1][str.length() + 1];
        story[0][0] = true;

        for (int i = 1; i < template.length() + 1; i++) {
            for (int j = 1; j < str.length() + 1; j++) {
                char tmpChar = template.charAt(i - 1);
                char strChar = str.charAt(j - 1);

                if (tmpChar == strChar) {
                    story[i][j] = story[i - 1][j - 1];
                }

                if (tmpChar == '?' || strChar == '?') {
                    story[i][j] = story[i - 1][j - 1];
                }

                if (tmpChar == '*' || strChar == '*') {
                    story[i][j] = (story[i - 1][j - 1])
                            ? true : (story[i - 1][j])
                            ? true : (story[i][j - 1])
                            ? true : false;
                }
            }
        }

        out.println(story[template.length()][str.length()] ? "YES" : "NO");
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
    }
}
