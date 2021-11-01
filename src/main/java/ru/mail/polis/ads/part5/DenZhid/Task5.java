package ru.mail.polis.ads.part5.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] a = new int[size];
        boolean[] isExposed = new boolean[size];
        permutation(a, isExposed, 0, size, out);
    }

    private static void permutation (int[] inArr, boolean[] isExposed, int start, int size, PrintWriter out) {
        if (start == size) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(inArr[i]);
                if (i != size - 1) {
                    sb.append(" ");
                }
            }
            out.println(sb);
        } else {
            for (int i = 0; i < size; i++) {
                if (!isExposed[i]) {
                    isExposed[i] = true;
                    inArr[start] = i + 1;
                    permutation(inArr, isExposed, start + 1, size, out);
                    isExposed[i] = false;
                }
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
