import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9630006
public class TemplateAndWord {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        char[] template;
        char[] word;
        if (s1.contains("*") || s1.contains("?")) {
            template = s1.toCharArray();
            word = s2.toCharArray();
        } else {
            template = s2.toCharArray();
            word = s1.toCharArray();
        }
        if (word[0] != template[0] && template[0] != '?' && template[0] != '*') {
            out.println("NO");
            return;
        }
        boolean[][] d = new boolean[template.length][word.length];
        d[0][0] = true;
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < word.length; j++) {
                if (i == j && i == 0) {
                    continue;
                }
                boolean diagonal = i >= 1 && j >= 1 && d[i - 1][j - 1];
                if (template[i] == word[j] || template[i] == '?') {
                    d[i][j] = diagonal;
                }
                if (template[i] == '*') {
                    boolean left = j >= 1 && d[i][j - 1];
                    boolean up = i >= 1 && d[i - 1][j];
                    d[i][j] = diagonal || up || left;
                }
            }
        }
        if (!d[template.length - 1][word.length - 1]) {
            out.println("NO");
            return;
        }
        out.println("YES");
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
