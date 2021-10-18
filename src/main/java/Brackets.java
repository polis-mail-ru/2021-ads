import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9570525
public class Brackets {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        if (str.equals("")) {
            out.println("");
            return;
        }
        String[][] s = new String[str.length()][str.length()];
        int[][] d = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                s[i][j] = "";
                if (i == j) {
                    s[i][i] = closeBracket(str.charAt(i));
                    d[i][i] = 1;
                    break;
                }
            }
        }
        for (int len = 2; len <= str.length(); len++) {
            for (int left = 0; left + len - 1 < str.length(); left++) {
                int right = left + len - 1;
                int minD = Integer.MAX_VALUE;
                String minS = "";
                for (int k = left; k <= right - 1; k++) {
                    if (d[left][k] + d[k + 1][right] < minD) {
                        minD = d[left][k] + d[k + 1][right];
                        minS = s[left][k] + s[k + 1][right];
                    }
                }
                if (paired(str.charAt(left), str.charAt(right))
                        && d[left + 1][right - 1] < minD) {
                    minD = d[left + 1][right - 1];
                    minS = str.charAt(left) + s[left + 1][right - 1] + str.charAt(right);
                }
                d[left][right] = minD;
                s[left][right] = minS;
            }
        }
        out.println(s[0][str.length() - 1]);
    }

    private static boolean paired(char bracket1, char bracket2) {
        return (bracket1 == '(' && bracket2 == ')')
                || (bracket1 == '[' && bracket2 == ']');
    }

    private static String closeBracket(char bracket) {
        switch (bracket) {
            case '(':
            case ')':
                return "()";
            case '[':
            case ']':
                return "[]";
            default:
                return "";
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
