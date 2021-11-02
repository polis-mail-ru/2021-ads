import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str1 = in.next();
        String str2 = in.next();
        String word;
        String reg;

        if (str1.contains("?") || str1.contains("*")) {
            reg = str1;
            word = str2;
        } else {
            reg = str2;
            word = str1;
        }
        boolean[][] d = new boolean[reg.length() + 1][word.length() + 1];
        d[0][0] = true;

        for (int i = 1; i <= reg.length(); i++) {
            for (int j = 1; j <= word.length(); j++) {
                if (reg.toCharArray()[i - 1] == '?' || reg.toCharArray()[i - 1] == word.toCharArray()[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];
                }
                if (reg.toCharArray()[i - 1] == '*' && (d[i - 1][j] || d[i][j - 1] || d[i - 1][j - 1])) {
                    d[i][j] = true;
                }
            }
        }

        if (d[reg.length()][word.length()])
            out.println("YES");
        else
            out.println("NO");

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
