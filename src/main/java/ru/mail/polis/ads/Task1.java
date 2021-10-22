package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int inputCount = in.nextInt();
        ArrayList<Integer> data = new ArrayList<>(inputCount + 1);
        data.add(0);
        for (int i = 0; i < inputCount; i++) {
            data.add(in.nextInt());
        }
        for (int i = 1; (2 * i + 1) <= data.size() - 1; i++) {
            if (data.get(i) > data.get(2 * i + 1)) {
                out.print("NO");
                return;
            }
        }
        for (int i = 1; (2 * i) <= data.size() - 1; i++) {
            if (data.get(i) > data.get(2 * i)) {
                out.print("NO");
                return;
            }
        }
        out.print("YES");
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
