package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class IsHeap {
    private IsHeap() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println("asdasdasd");
        int n = in.nextInt();
        int[] maybeHeap = new int[n];
        for (int i = 0; i < n; i++) {
            maybeHeap[i] = in.nextInt();
        }
        for (int i = 0; i < n / 2; i++) {
            if (maybeHeap[i] > maybeHeap[i * 2 + 1] || (i * 2 + 2 < n && maybeHeap[i] > maybeHeap[i * 2 + 2])) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
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
