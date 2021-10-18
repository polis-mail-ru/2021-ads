import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
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
                if ((s.charAt(strN) == '(' && s.charAt(colN) == ')') ||
                        (s.charAt(strN) == '[' && s.charAt(colN) == ']')) {
                    d[strN][colN] = d[strN + 1][colN - 1];
                } else {
                    // Case when i j not fits
                    int minSymsToCorrect = d[strN][strN] + d[strN + 1][colN];
                    for (int k = strN + 1; k < colN; k++) {
                        if (d[strN][k] + d[k + 1][colN] < minSymsToCorrect) {
                            minSymsToCorrect = d[strN][k] + d[k + 1][colN];
                        }
                    }
                    d[strN][colN] = minSymsToCorrect;
                }
            }
        }

        out.println(d[0][s.length() - 1]);
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
