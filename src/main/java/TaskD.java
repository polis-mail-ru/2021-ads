import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private static boolean[][] validity;

    private TaskD() {
        // Should not be instantiated
    }

    private static boolean checkValidity(int i, int j) {
        if (i < 0 || j < 0 || i > validity.length || j > validity[0].length) {
            return false;
        }
        return validity[i][j];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String line1 = in.next();
        String line2 = in.next();

        boolean isRegex = false;
        for (int i = 0; i < line1.length(); i++) {
            if (line1.charAt(i) == '?' || line1.charAt(i) == '*') {
                isRegex = true;
                break;
            }
        }
        String regex, text;

        if (isRegex) {
            regex = line1;
            text = line2;
        } else {
            regex = line2;
            text = line1;
        }
        validity = new boolean[regex.length()][text.length()];

        if (regex.charAt(0) == '?') {
            validity[0][0] = true;
        } else if (regex.charAt(0) == '*') {
            for (int i = 0; i < text.length(); i++) {
                validity[0][i] = true;
            }
        } else {
            if (regex.charAt(0) == text.charAt(0)) {
                validity[0][0] = true;
            }
        }

        for (int i = 1; i < regex.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (regex.charAt(i) == '?') {
                    validity[i][j] = checkValidity(i - 1, j - 1);
                } else if (regex.charAt(i) == '*') {
                    validity[i][j] = checkValidity(i - 1, j) || checkValidity(i, j - 1);
                } else {
                    if (regex.charAt(i) == text.charAt(j)) {
                        validity[i][j] = checkValidity(i - 1, j - 1);
                    } else {
                        validity[i][j] = false;
                    }
                }
            }
        }
        if (validity[regex.length() - 1][text.length() - 1]) {
            out.println("YES");
        } else {
            out.println("NO");
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
}
