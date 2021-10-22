package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BracketsTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String data = in.next();
        int n = data.length();
        int[][] dynamicMagic = new int[n][n];
        for (int i = 0; i < n; i++) {
            dynamicMagic[i][i] = 1;
        }

        for (int count = 1; count < n; count++) {//diagonal traversal
            for (int i = 0; i < n - count; i++) {
                int j = i + count;
                int min = dynamicMagic[i][i] + dynamicMagic[i + 1][j];
                for (int k = i; k < j; k++) {
                    if (dynamicMagic[i][k] + dynamicMagic[k + 1][j] < min) {
                        min = dynamicMagic[i][k] + dynamicMagic[k + 1][j];
                    }
                }
                if (isClosed(data.charAt(i), data.charAt(j))) {
                    if (dynamicMagic[i + 1][j - 1] < min) {
                        dynamicMagic[i][j] = dynamicMagic[i + 1][j - 1];
                        continue;
                    }
                }
                dynamicMagic[i][j] = min;
            }
        }
        out.println(restore(data, 0, n - 1, dynamicMagic));
    }

    private static boolean isClosed(char a1, char a2) {
        return (a1 == '(' && a2 == ')') || (a1 == '[' && a2 == ']');
    }

    private static String restore(String data, int i, int j, int[][] dynamic) {
        if (i > j) {
            return "";
        }
        if (i == j) {
            if (data.charAt(i) == '(' || data.charAt(i) == ')') {
                return "()";
            }
            return "[]";
        }
        int kOfMin = i;
        int min = dynamic[i][i] + dynamic[i + 1][j];
        for (int k = i; k < j; k++) {
            if (dynamic[i][k] + dynamic[k + 1][j] < min) {
                min = dynamic[i][k] + dynamic[k + 1][j];
                kOfMin = k;
            }
        }
        if (isClosed(data.charAt(i), data.charAt(j))) {
            if (dynamic[i + 1][j - 1] < min) {
                return data.charAt(i) + restore(data, i + 1, j - 1, dynamic) + data.charAt(j);
            }
        }
        return restore(data, i, kOfMin, dynamic) + restore(data, kOfMin + 1, j, dynamic);
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
                    String line = reader.readLine();
                    if (line == null) {
                        return "";
                    }
                    tokenizer = new StringTokenizer(line);
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
