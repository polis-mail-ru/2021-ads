package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int i = 1;
        int j = 2;
        long iSquare = 1;
        long jCube = 8;
        for (int count = 1; ; count++) {
            if (iSquare <= jCube) {
                if (count == n) {
                    out.println(iSquare);
                    return;
                }
                if (iSquare == jCube){
                    j++;
                    jCube = (long) j * j * j;
                }
                i++;
                iSquare = (long) i * i;

            } else {
                if (count == n) {
                    out.println(jCube);
                    return;
                }
                j++;
                jCube = (long) j * j * j;
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
