package ru.mail.polis.ads.part4.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class BracketSequence {
    private BracketSequence() {
        // Should not be instantiated
    }

    private static boolean checkRightBrackets(char left, char right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']');
    }

    private static String closeBrackets(char bracket) {
        if (bracket == '[' || bracket == ']') {
            return "[]";
        }
        return bracket == '(' || bracket == ')' ? "()" : "";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String bracketSequence = in.nextLine();

        if (bracketSequence == null || bracketSequence.isEmpty()) {
            return;
        }

        int n = bracketSequence.length();

        int[][] d = new int[n][n];
        String[][] ans = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = "";
            }
        }

        int right;
        int left;
        int minK;
        int minD;
        StringBuilder minS;

        for (int len = 0; len < n; len++) {
            right = len;
            left = 0;
            while (right < n) {
                if (left == right) {
                    d[left][right] = 1;
                    ans[right][left] = closeBrackets(bracketSequence.charAt(right));
                } else {
                    minK = left;
                    for (int k = left; k < right; k++) {
                        if (d[left][k] + d[k + 1][right] < d[left][minK] + d[minK + 1][right]) {
                            minK = k;
                        }
                    }
                    minD = d[left][minK] + d[minK + 1][right];
                    minS = new StringBuilder().append(ans[left][minK])
                            .append(ans[minK + 1][right]);

                    if (checkRightBrackets(bracketSequence.charAt(left), bracketSequence.charAt(right)) &&
                            d[left + 1][right - 1] < minD) {
                        minD = d[left + 1][right - 1];
                        minS = new StringBuilder().append(bracketSequence.charAt(left))
                                .append(ans[left + 1][right - 1])
                                .append(bracketSequence.charAt(right));
                    }

                    d[left][right] = minD;
                    ans[left][right] = minS.toString();
                }

                left++;
                right++;
            }
        }
        out.println(ans[0][n - 1]);
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

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
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
