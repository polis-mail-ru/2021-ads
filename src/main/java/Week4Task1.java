import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week4Task1 {
    private static int[][] d;
    private static String input;

    private Week4Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            input = in.readLine();
        } catch (IOException e) {
            return;
        }
        if (input.equals("")) {
            out.println("");
            return;
        }
        int length = input.length();
        d = new int[length][length];
        for (int i = 0; i < length; i++) {
            d[i][i] = 1;
        }
        for (int k = 1; k < length; k++) {
            for (int i = 0; i < length; i++) {
                int j = i + k;
                if (j >= length) {
                    break;
                }
                int currentLength = Integer.MAX_VALUE;
                if (input.charAt(i) == '(' && input.charAt(j) == ')'
                        || input.charAt(i) == '[' && input.charAt(j) == ']') {
                    currentLength = d[i + 1][j - 1];
                }
                for (int m = i; m < j; m++) {
                    currentLength = Math.min(currentLength, d[i][m] + d[m + 1][j]);
                }
                d[i][j] = currentLength;
            }
        }
        out.println(RightBrackets(0, length - 1));
    }

    private static String RightBrackets(int l, int r) {
        if (l > r) {
            return "";
        }
        if (l == r) {
            switch (input.charAt(l)) {
                case '(':
                case ')':
                    return "()";
                case '[':
                case ']':
                    return "[]";
            }
        }
        int separator = input.charAt(l) == '(' && input.charAt(r) == ')'
                || input.charAt(l) == '[' && input.charAt(r) == ']' ? d[l + 1][r - 1] : Integer.MAX_VALUE;
        if (separator == d[l][r]) {
            return input.charAt(l) + RightBrackets(l + 1, r - 1) + input.charAt(r);
        }
        for (int m = l; m < r; m++) {
            if (d[l][m] + d[m + 1][r] == d[l][r]) {
                return RightBrackets(l, m) + RightBrackets(m + 1, r);
            }
        }
        return "";
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

        String readLine() throws IOException {
            return reader.readLine();
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
