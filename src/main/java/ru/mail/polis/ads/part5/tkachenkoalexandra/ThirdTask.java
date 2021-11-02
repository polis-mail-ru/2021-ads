package part5.tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class ThirdTask {

    private static final int MAX = 1000;

    private ThirdTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = IntStream.range(0, checkCount(in.nextInt())).map(i -> in.nextInt()).toArray();
        int[] dpTable = new int[array.length];
        int maxLengthIndex = 0;
        for (int i = 0; i < array.length; i++) {
            dpTable[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] != 0 && array[i] % array[j] == 0) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
            if (dpTable[i] > dpTable[maxLengthIndex]) {
                maxLengthIndex = i;
            }
        }
        out.println(dpTable[maxLengthIndex]);
    }

    private static int checkCount(int n) {
        if ((n <= 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
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

