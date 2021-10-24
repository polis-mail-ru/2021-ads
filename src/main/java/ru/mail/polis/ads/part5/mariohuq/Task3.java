package ru.mail.polis.ads.part5.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 264. Наибольшая последовательнократная подпоследовательность
 * <p>
 * https://www.e-olymp.com/ru/submissions/9636029
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] sequence = new int[in.nextInt()];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = in.nextInt();
        }
        int[] maxLengths = new int[sequence.length];
        int answer = 0;
        for (int i = 0; i < maxLengths.length; i++) {
            maxLengths[i] = 1 + maxLengthBefore(maxLengths, sequence, i);
            answer = Math.max(answer, maxLengths[i]);
        }
        out.println(answer);
    }

    private static int maxLengthBefore(int[] maxLengths, int[] sequence, int end) {
        int maxLengthBefore = 0;
        for (int i = 0; i < end; i++) {
            if (sequence[end] == 0 || sequence[i] != 0 && sequence[end] % sequence[i] == 0) {
                maxLengthBefore = Math.max(maxLengthBefore, maxLengths[i]);
            }
        }
        return maxLengthBefore;
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
