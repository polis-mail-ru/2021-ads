import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9723077
 */
public final class Week5Task4 {
    private Week5Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String word = in.next();
        String template = in.next();

        if (word.contains("*") || word.contains("?")) {
            String temp = word;
            word = template;
            template = temp;
        }

        boolean[][] dynamic = new boolean[template.length()][word.length()];
        dynamic[0][0] = true;

        for (int i = 0; i < template.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                boolean diagonalDynamic = i > 0 && j > 0 && dynamic[i - 1][j - 1];

                if (template.charAt(i) == word.charAt(j) || template.charAt(i) == '?') {
                    dynamic[i][j] = diagonalDynamic;
                }

                if (template.charAt(i) == '*') {
                    boolean leftDynamic = j > 0 && dynamic[i][j - 1];
                    boolean upDynamic = i > 0 && dynamic[i - 1][j];
                    dynamic[i][j] = diagonalDynamic || leftDynamic || upDynamic;
                }
            }
        }

        out.println(dynamic[template.length() - 1][word.length() - 1] ? "YES" : "NO");
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
