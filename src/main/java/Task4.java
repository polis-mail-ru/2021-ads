
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        char[] pattern = in.next().toCharArray();
        char[] str = in.next().toCharArray();

        boolean hasQuantifier = false;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' || pattern[i] == '?') {
                hasQuantifier = true;
                break;
            }
        }
        if (!hasQuantifier) {
            if (pattern.length != str.length) {
                out.println("NO");
            } else {
                for (int i = 0; i < pattern.length; i++) {
                    if (pattern[i] != str[i]) {
                        out.println("NO");
                        return;
                    }
                }
            }
            out.println("YES");
            return;
        }

        boolean[][] d = new boolean[str.length][pattern.length];

        if (str[0] == pattern[0] || pattern[0] == '?' || pattern[0] == '*') {
            d[0][0] = true;
        } else {
            out.println("NO");
            return;
        }

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (pattern[j] == '*') {
                    d[i][j] = j - 1 >= 0 && d[i][j - 1] || i - 1 >= 0 && d[i - 1][j];
                } else if (pattern[j] == '?') {
                    d[i][j] = i - 1 >= 0 && j - 1 >= 0 && d[i - 1][j - 1];
                } else if (str[i] == pattern[j]) {
                    d[i][j] = i - 1 >= 0 && j - 1 >= 0 && d[i - 1][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }

        if (d[str.length - 1][pattern.length - 1]) {
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
