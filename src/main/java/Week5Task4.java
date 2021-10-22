import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week5Task4 {
    private Week5Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        String template;
        if (str.contains("*") || str.contains("?")) {
            template = str;
            str = in.next();
        }
        else {
            template = in.next();
        }
        char[] strC = str.toCharArray();
        char[] templateC = template.toCharArray();
        out.println(isStringMatchTemplate(templateC, strC) ? "YES" : "NO");
    }

    private static boolean isStringMatchTemplate(char[] template, char[] str) {
        boolean[][] d = new boolean[template.length + 1][str.length + 1];
        if (template[0] == str[0] || template[0] == '?' || template[0] == '*') {
            d[1][1] = true;
        }
        for (int i = 1; i <= template.length; i++) {
            for (int j = 1; j <= str.length; j++) {
                if (i != 1 || j != 1) {
                    if (template[i - 1] == '*') {
                        d[i][j] = d[i][j - 1] || d[i - 1][j];
                    }
                    if (template[i - 1] == '?' || template[i - 1] == str[j - 1]) {
                        d[i][j] = d[i - 1][j - 1];
                    }
                }
            }
        }
        return d[template.length][str.length];
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
