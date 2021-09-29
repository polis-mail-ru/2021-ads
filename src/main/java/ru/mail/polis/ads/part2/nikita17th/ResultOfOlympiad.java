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
public final class ResultOfOlympiad {
    private ResultOfOlympiad() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] array = new int[n][2];

        for (int i = 0; i < n; i++) {
            array[i][0] = in.nextInt();
            array[i][1] = in.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j][1] > array[maxIndex][1]) {
                    maxIndex = j;
                } else if (array[j][1] == array[maxIndex][1] && array[j][0] < array[maxIndex][0]) {
                    maxIndex = j;
                }
            }

            out.println(array[maxIndex][0] + " " + array[maxIndex][1]);
            if (maxIndex != i) {
                array[maxIndex][0] = array[i][0];
                array[maxIndex][1] = array[i][1];
            }
        }

        out.println(array[n - 1][0] + " " + array[n - 1][1]);

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
