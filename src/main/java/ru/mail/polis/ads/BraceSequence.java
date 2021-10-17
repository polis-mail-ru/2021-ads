package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BraceSequence {
    public static String str;

    //https://www.e-olymp.com/ru/submissions/9567342
    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            str = in.readLine();
        } catch (IOException e) {
            return;
        }
        int length = str.length();
        if (length == 0) {
            out.println("");
            return;
        }

        int[][] array = new int[length][length];
        for (int i = 0; i < length; i++) {
            array[i][i] = 1;
        }

        for (int j = 1; j < length; j++) {
            for (int i = 0; i <= length - j - 1; i++) {
                int m = i + j;
                int min = Integer.MAX_VALUE;
                if (isPairedBraces(i, m)) {
                    min = array[i + 1][m - 1];
                }
                for (int k = i; k < m; k++) {
                    min = Math.min(min, array[i][k] + array[k + 1][m]);
                }
                array[i][m] = min;
            }
        }

        out.print(restoreBrackets(0, length - 1, array));
    }

    private static boolean isPairedBraces(int index1, int index2) {
        char ch2 = str.charAt(index2);
        switch (str.charAt(index1)) {
            case '(':
                return ch2 == ')';
            case '[':
                return ch2 == ']';
            default:
                return false;
        }
    }


    private static String restoreBrackets(int l, int r, int[][] array) {
        if (l > r) {
            return "";
        }
        if (l == r) {
            switch (str.charAt(l)) {
                case '(':
                case ')':
                    return "()";
                case '[':
                case ']':
                    return "[]";
            }
        }
        if (isPairedBraces(l, r) && array[l + 1][r - 1] == array[l][r]) {
            return str.charAt(l) + restoreBrackets(l + 1, r - 1, array) + str.charAt(r);
        }
        for (int m = l; m < r; m++) {
            if (array[l][m] + array[m + 1][r] == array[l][r]) {
                return restoreBrackets(l, m, array) + restoreBrackets(m + 1, r, array);
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        String readLine() throws IOException {
            return reader.readLine();
        }

    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }
}
