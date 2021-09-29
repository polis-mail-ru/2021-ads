package ru.mail.polis.ads.part2.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class MergeSequence {
    private MergeSequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int i = 1;
        int j = 1;

        long nowNumberSquare;
        long nowNumberCube;
        long currentNumber = 0;

        for (int k = 0; k < n; k++) {
            nowNumberSquare = (long) Math.pow(i, 2);
            nowNumberCube = (long) Math.pow(j, 3);

            currentNumber = Math.min(nowNumberSquare, nowNumberCube);

            if (currentNumber == nowNumberSquare) {
                i++;
            }

            if (currentNumber == nowNumberCube) {
                j++;
            }
        }

        out.println(currentNumber);
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
