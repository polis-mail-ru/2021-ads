package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class PermutationTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        StringBuilder data = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            data.append(i);
        }
        permutation("", data.toString(), out);
    }

    private static void permutation(String prefix, String str, PrintWriter out) {
        int n = str.length();
        if (n == 0) {
            out.println(prefix.replace("", " ").trim());
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), out);
            }
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
