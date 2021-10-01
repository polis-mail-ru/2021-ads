package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int diff = 107;
        int n = in.nextInt();
        int[] arr = IntStream.range(0, n).map(i -> in.nextInt()).toArray();
        if (arr.length == 0) {
            return;
        }
        int min = Arrays.stream(arr).min().getAsInt();
        int[] counter = new int[diff + 1];
        Arrays.stream(arr).forEach((element) -> counter[element - min] += 1);
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++) {
                if (i != 0 || j != 0) {
                    out.print(" ");
                }
                out.print(min + i);
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
