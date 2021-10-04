package ru.mail.polis.ads.part2.tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class SecondTask {

    private static final int MAX = 100000;

    private SecondTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = IntStream.range(0, checkCount(in.nextInt())).map(i -> in.nextInt()).toArray();
        countingSort(array, 0, array.length - 1);
        Arrays.stream(array).mapToObj(j -> j + " ").forEach(out::print);
    }

    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
    }

    static void countingSort(int[] array, int from, int to) {
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();
        int c[] = new int[max - min + 1];
        int output[] = new int[array.length];
        IntStream.range(0, array.length).forEach(i -> c[array[i] - min]++);
        IntStream.range(1, c.length).forEach(i -> c[i] += c[i - 1]);
        IntStream.iterate(array.length - 1, i -> i >= 0, i -> i - 1).forEach(i -> {
            output[c[array[i] - min] - 1] = array[i];
            c[array[i] - min]--;
        });
        IntStream.range(0, array.length).forEach(i -> array[i] = output[i]);
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
