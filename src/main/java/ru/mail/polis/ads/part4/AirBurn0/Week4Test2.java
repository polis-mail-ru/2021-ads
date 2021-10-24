package ru.mail.polis.ads.part4.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9630320
public final class Week4Test2 {
    private Week4Test2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[][] field = new int[in.nextInt()][in.nextInt()];
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[0].length; ++j) {
                field[i][j] = in.nextInt();
            }
        }
        int i = 0;
        int j = 0;
        for (i = field.length - 1; i >= 0; --i) {
            for (j = 0; j < field[0].length; ++j) {
                field[i][j] += Math.max(i == field.length - 1 ? 0 : field[i + 1][j], j == 0 ? 0 : field[i][j - 1]);
            }
        }
        StringBuilder answer = new StringBuilder();
        i = 0;
        j = field[0].length - 1;
        while (i < field.length - 1 && j > 0) {
            if (field[i + 1][j] > field[i][j - 1]) {
                answer.append("F");
                ++i;
            } else {
                answer.append("R");
                --j;
            }
        }

        while (i < field.length - 1) {
            answer.append("F");
            ++i;
        }
        while (j > 0) {
            answer.append("R");
            --j;
        }
        out.println(answer.reverse().toString());
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
