package ru.mail.polis.ads.part5.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class LongestConsecutiveSubsequence {
    private LongestConsecutiveSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] sequence = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = in.nextInt();
        }

        int maxSeq = 0;
        int prevMax;
        for (int i = 0; i < n; i++) {
            prevMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (sequence[j] != 0 && d[j] > prevMax && sequence[i] % sequence[j] == 0) {
                    prevMax = d[j];
                }
            }
            d[i] += prevMax + 1;
            maxSeq = Math.max(maxSeq, d[i]);
        }


        out.println(maxSeq);

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
