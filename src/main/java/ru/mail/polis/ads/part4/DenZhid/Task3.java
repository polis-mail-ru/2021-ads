package ru.mail.polis.ads.part4.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int firstSize = in.nextInt();
        int[] firstSeq = new int[firstSize];
        for (int i = 0; i < firstSize; i++) {
            firstSeq[i] = in.nextInt();
        }
        int secondSize = in.nextInt();
        int[] secondSeq = new int[secondSize];
        for (int i = 0; i < secondSize; i++) {
            secondSeq[i] = in.nextInt();
        }
        int[][] dynamicMatrix = new int[firstSize + 1][secondSize + 1];
        for (int i = 0; i < firstSize + 1; i++) {
            dynamicMatrix[i][0] = 0;
        }
        for (int j = 0; j < secondSize + 1; j++) {
            dynamicMatrix[0][j] = 0;
        }
        for (int i = 1; i < firstSize + 1; i++) {
            for (int j = 1; j < secondSize + 1; j++) {
                if (firstSeq[i - 1] == secondSeq[j - 1]) {
                    dynamicMatrix[i][j] = dynamicMatrix[i - 1][j - 1] + 1;
                } else {
                    dynamicMatrix[i][j] = Math.max(dynamicMatrix[i - 1][j], dynamicMatrix[i][j - 1]);
                }
            }
        }
        out.println(dynamicMatrix[firstSize][secondSize]);
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
