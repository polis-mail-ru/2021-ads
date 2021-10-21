import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;


public final class HomeWork5Task5 {

    private static int[] a;

    private HomeWork5Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        a = IntStream.range(1, in.nextInt() + 1).toArray();
        print(a, out);
        while (hasNewPermutation()) {
            print(a, out);
        }
    }

    private static boolean hasNewPermutation() {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int currentMin = a[i + 1];
                int currentMinIndex = i + 1;
                for (int j = i + 2; j < a.length; j++)
                    if (a[j] > a[i] && a[j] < currentMin) {
                        currentMin = a[j];
                        currentMinIndex = j;
                    }
                swap(a, i, currentMinIndex);
                Arrays.sort(a, i + 1, a.length);
                return true;
            }
        }
        return false;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void print(int[] a, final PrintWriter out) {
        for (int i : a) {
            out.print(i + " ");
        }
        out.println();

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