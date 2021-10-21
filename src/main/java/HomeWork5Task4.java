import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork5Task4 {

    private static char[] templateInChars;
    private static char[] stringInChars;
    private static boolean[][] d;

    private HomeWork5Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            String string = in.reader.readLine();
            String template;
            if (string.contains("*") || string.contains("?")) {
                template = string;
                string = in.reader.readLine();
            } else {
                template = in.reader.readLine();
            }
            template = template.replaceAll("\\*+", "*");
            templateInChars = template.toCharArray();
            stringInChars = string.toCharArray();
            d = new boolean[templateInChars.length + 1][stringInChars.length + 1];
            for (int i = 1; i <= templateInChars.length; i++) {
                if (templateInChars[i - 1] == '*') {
                    for (int j = 1; j <= stringInChars.length; j++) {
                        d[i][j] = d[i][j - 1] || d[i - 1][j];
                        if (i == 1) {
                            d[i][j] = true;
                        }
                    }
                } else if (templateInChars[i - 1] == '?') {
                    for (int j = 1; j <= stringInChars.length; j++) {
                        d[i][j] = true;
                    }
                } else {
                    for (int j = 1; j <= stringInChars.length; j++) {
                        if (templateInChars[i - 1] == stringInChars[j - 1]) {
                            d[i][j] = true;
                        }
                    }
                }
            }
            if (isCorrect(templateInChars.length, stringInChars.length)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isCorrect(int i, int j) {
        while (d[i][j]) {
            if (templateInChars[i - 1] == '*') {
                if (d[i - 1][j] && isCorrect(i - 1, j)) {
                    return true;
                }
                while (d[i][j - 1]) {
                    j--;
                    if (isCorrect(i - 1, j)) {
                        return true;
                    }
                }
                return d[i - 1][j - 1] && isCorrect(i - 1, j - 1);
            } else {
                i--;
                j--;
            }
        }
        return (i == 0 || j == 0);
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