package ru.mail.polis.ads.part2.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    final static int DIFFERENCE = 107;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] numCounts = new int[DIFFERENCE * 2];
        int firstNum = in.nextInt();
        int shift = firstNum - DIFFERENCE;
        numCounts[firstNum - shift]++;
        for (int i = 0; i < n - 1; i++) {
            numCounts[in.nextInt() - shift]++;
        }
        for (int i = 0; i < 214; i++) {
            if (numCounts[i] > 0) {
                for (int j = 0; j < numCounts[i]; j++) {
                    out.print(i + shift + " ");
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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