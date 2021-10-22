package ru.artyom.scheredin.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] array = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        int k = in.nextInt();

        int temp[] = new int[n + 2];
        int max = 0;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= k; j++) {
                if(i - j >= 0) {
                    max = Math.max(temp[i - j], max);
                }
            }
            temp[i] = array[i] + max;
            max = temp[i];
        }
        out.println(temp[n + 1]);
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
