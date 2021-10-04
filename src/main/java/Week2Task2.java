import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Week2Task2 {
    private Week2Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNumbers = in.nextInt();
        int[] data = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            data[i] = in.nextInt();
        }
        sort(data,0, data.length);
        for (int num : data) {
            out.print(num + " ");
        }
    }

    private static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid);
        sort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(int[] array, int fromInclusive, int mid, int toExclusive) {
        int[] temp = new int[toExclusive - fromInclusive];
        int i = 0;
        int i1 = fromInclusive;
        int i2 = mid;
        while (i1 < mid || i2 < toExclusive) {
            if (i1 < mid && (i2 == toExclusive || array[i1] <= array[i2])) {
                temp[i] = array[i1];
                i1++;
            } else {
                temp[i] = array[i2];
                i2++;
            }
            i++;
        }
        for (i = 0; i < toExclusive - fromInclusive; i++) {
            array[i + fromInclusive] = temp[i];
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
