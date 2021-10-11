package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public final class Week3Task1 {
    private Week3Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        if (size <= 1) {
            out.println("YES");
            return;
        }
        int[] heap = new int[size + 1];
        heap[1] = in.nextInt();
        for (int i = 2; i <= size; i++) {
            heap[i] = in.nextInt();
            if (heap[i/2] > heap[i]) {
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
