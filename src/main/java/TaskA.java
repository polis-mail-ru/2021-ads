import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = null;

        //Without this I can't pass last test, maybe  they check with empty file
        try {
            s = in.nextLine();
        } catch (IOException e) {
            return;
        }

        if(s.length() == 0) {
            out.println("");
            return;
        }
        /////

        int[][] d = new int[s.length()][s.length()];

        //0s before main diag and 1s on main diag
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (i > j) {
                    d[i][j] = 0;
                } else if (i == j) {
                    d[i][j] = 1;
                }
            }
        }

        //Going through matrix upper diags
        for (int diagN = 1; diagN < s.length(); diagN++) {
            for (int l = 0; l + diagN < s.length(); l++) {
                int strN = l;
                int colN = l + diagN;
                int minSymsToCorrect = d[strN][strN] + d[strN + 1][colN];

                if (isClosedBrackets(s, strN, colN)) {
                    minSymsToCorrect = d[strN + 1][colN - 1];
                }

                for (int k = strN + 1; k < colN; k++) {
                    if (d[strN][k] + d[k + 1][colN] < minSymsToCorrect) {
                        minSymsToCorrect = d[strN][k] + d[k + 1][colN];
                    }
                }

                d[strN][colN] = minSymsToCorrect;
            }
        }


        String ans = restoreSubs(s, d, 0, s.length() - 1);
        out.println(ans);
    }

    private static String restoreSubs(String s, int[][] d, int i, int j) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    return "()";
                case '[':
                case ']':
                    return "[]";
            }
        }

        if (i > j) {
            return "";
        }

        if (isClosedBrackets(s, i, j) && d[i][j] == d[i + 1][j - 1]) {
            return s.charAt(i) + restoreSubs(s, d, i + 1, j - 1) + s.charAt(j);
        }

        for (int k = i; k < j; k++) {
            if (d[i][j] == d[i][k] + d[k + 1][j]) {
                return restoreSubs(s, d, i, k) + restoreSubs(s, d, k + 1, j);
            }
        }

        return "";
    }

    private static boolean isClosedBrackets(String s, int i, int j) {
        if (s.charAt(i) == '(' && s.charAt(j) == ')' || s.charAt(i) == '[' && s.charAt(j) == ']')
            return true;
        return false;
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

        String nextLine() throws IOException {
            return reader.readLine();
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
