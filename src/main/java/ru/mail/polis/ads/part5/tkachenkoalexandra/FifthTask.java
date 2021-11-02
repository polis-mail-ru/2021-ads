package part5.tkachenkoalexandra;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class FifthTask {

    private static final int MAX = 8;

    private FifthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = IntStream.range(1, checkCount(in.nextInt()) + 1).toArray();
        String strOfInts = Arrays.toString(array).replaceAll("\\[|\\]|,|\\s", "");
        List<String> list = new ArrayList<>();
        permutation("", 0, strOfInts.length(), strOfInts, list);
        list.forEach(out::println);
    }

    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
    }

    private static void permutation(String res, int curr, int length, String input, List<String> list) {
        if (length == curr) {
            list.add(res.replace("", " ").trim());
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            permutation(res + input.charAt(i), curr + 1, length,
                    input.substring(0, i) + input.substring(i + 1), list);
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

        boolean hasNext() {
            return (tokenizer == null) || (tokenizer.hasMoreTokens());
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