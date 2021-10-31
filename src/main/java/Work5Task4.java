import java.io.*;
import java.util.StringTokenizer;


public class Work5Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String first = in.next();
        String second = in.next();
        String template, word;

        if (first.contains("?") || first.contains("*")) {
            template = first;
            word = second;
        } else {
            template = second;
            word = first;
        }

        boolean[][] matrix = new boolean[template.length()][word.length()];
        for (int i = 0; i < template.length(); i++) {
            char current = template.charAt(i);
            for (int j = 0; j < word.length(); j++) {
                switch (current) {
                    case '*': {
                        if ((i == 0) || matrix[i - 1][j] || (j != 0 && matrix[i][j - 1])) {
                            matrix[i][j] = true;
                        }
                        break;
                    }
                    case '?': {
                        if ((i != 0 && j != 0 && matrix[i - 1][j - 1]) || (i == 0 && j == 0) || (j == 0 && matrix[i - 1][j])) {
                            matrix[i][j] = true;
                        }
                        break;
                    }
                    default: {
                        if ((current == word.charAt(j)) && ((i != 0 && j != 0 && matrix[i - 1][j - 1])
                                || (i == 0 && j == 0) || (j == 0 && matrix[i - 1][j]))) {
                            matrix[i][j] = true;
                        }
                        break;
                    }
                }
            }
        }

        if (matrix[template.length() - 1][word.length() - 1]) {
            out.println("YES");
        } else {
            out.println("NO");
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
