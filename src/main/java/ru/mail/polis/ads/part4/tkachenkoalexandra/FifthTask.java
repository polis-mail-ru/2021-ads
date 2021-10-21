package tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class FifthTask {

    private static final int MAX = 50000;

    private FifthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = IntStream.range(0, checkCount(in.nextInt())).map(i -> in.nextInt()).toArray();
        boolean asc = false;
        boolean desc = false;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                asc = true;
            }
            if (array[i] > array[i + 1]) {
                desc = true;
            }
        }
        if (asc && desc) {
            int[] temp = new int[array.length];
            out.println(countInv(array, temp, 0, array.length - 1));
            return;
        }
        out.println(asc ? 0 : array.length * (array.length - 1) / 2);
    }

    private static int checkCount(int n) {
        if ((n < 0) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
    }

    private static int countInv(int[] array, int[] temp, int from, int to) {
        if (to - from <= 0) {
            return 0;
        }
        int mid = (from + to) >>> 1;
        return countInv(array, temp, from, mid) + countInv(array, temp, mid + 1, to)
                + countSplitInv(array, temp, from, mid, to);
    }

    private static int countSplitInv(int[] array, int[] a, int from, int mid, int to) {
        int p = from;
        int q = mid + 1;
        int k = 0;
        int inversions = 0;
        for (int i = from; i < to + 1; i++) {
            if (p > mid) {
                a[k++] = array[q++];
            } else if (q > to) {
                a[k++] = array[p++];
            } else if (array[p] < array[q]) {
                a[k++] = array[p++];
            } else {
                a[k++] = array[q++];
                inversions += mid + 1 - p;
            }
        }
        for (int i = 0; i < k; i++) {
            array[from++] = a[i];
        }
        return inversions;
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