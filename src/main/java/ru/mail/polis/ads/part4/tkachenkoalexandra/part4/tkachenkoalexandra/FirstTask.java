//https://www.e-olymp.com/ru/submissions/9642956
package tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public final class FirstTask {

    private static final int MAX = 100;

    private FirstTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.nextLine().trim();
        if (s.isEmpty()) {
            return;
        }
        if (!Pattern.compile("[(*\\[)\\]]*").matcher(s).matches()) {
            throw new IllegalArgumentException("The source data of the s contains an unresolved character.\n");
        }
        if (s.length() > MAX) {
            throw new IllegalArgumentException("The length of the s exceeds the limit.\n");
        }
        System.out.println(new Sequence(s).restore(0, s.length() - 1));
    }

    static class Sequence {
        private final String s;
        private final int length;
        private final int[][] d;

        public Sequence(String s) {
            this.s = s;
            this.length = s.length();
            this.d = new int[length][length];
        }

        private void fill() {
            int minCost;
            for (int i = 0; i < length; i++) {
                for (int j = i; j >= 0; j--) {
                    if (i == j) {
                        d[i][j] = 1;
                    } else {
                        minCost = Integer.MAX_VALUE;
                        if (isSimilarBrackets(i, j)) {
                            minCost = d[j + 1][i - 1];
                        }
                        for (int k = j; k < i; k++) {
                            minCost = Math.min(minCost, d[j][k] + d[k + 1][i]);
                        }
                        d[j][i] = minCost;
                    }
                }
            }
        }

        private boolean isSimilarBrackets(int i, int j) {
            return (s.charAt(j) == '(' && s.charAt(i) == ')') || (s.charAt(j) == '[' && s.charAt(i) == ']');
        }

        public StringBuilder restore(int i, int j) {
            fill();
            if (d[0][length - 1] == 0) {
                return new StringBuilder(s);
            }
            if (i > j) {
                return new StringBuilder("");
            }
            if (i == j) {
                return new StringBuilder(s.charAt(i) == '(' || s.charAt(i) == ')' ? "()" : "[]");
            } else {
                if (isSimilarBrackets(j, i) && d[i + 1][j - 1] == d[i][j]) {
                    return new StringBuilder().append(s.charAt(i)).append(restore(i + 1, j - 1)).append(s.charAt(j));
                }
                for (int k = i; k < j; k++) {
                    if (d[i][k] + d[k + 1][j] == d[i][j]) {
                        return restore(i, k).append(restore(k + 1, j));
                    }
                }
            }
            return null;
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

        boolean hasNext() {
            return (tokenizer == null) || (tokenizer.hasMoreTokens());
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
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

