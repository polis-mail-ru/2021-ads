package ru.mail.polis.ads.part5.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/264
public final class Week5Test3 {
    private Week5Test3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] input1 = new int[in.nextInt()];
        for (int i = 0; i < input1.length; ++i) {
            input1[i] = in.nextInt();
        }
        
        int[][] matrix = new int[input1.length + 1][input1.length + 1];
        for (int i = 1; i <= input1.length; ++i) {
            for (int j = 1; j <= i; ++j) {
                if (input1[i - 1] % input1[j - 1] == 0) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else if (matrix[i - 1][j] < matrix[i][j - 1]) {
                    matrix[i][j] = matrix[i][j - 1];
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        out.println(matrix[input1.length][input1.length]);
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
