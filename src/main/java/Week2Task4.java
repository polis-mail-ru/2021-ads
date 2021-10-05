import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task4 {
    private Week2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNumbers = in.nextInt();
        int[] data = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            data[i] = in.nextInt();
        }
       sort(data);

        int last = data[0];
        int countOfUniq = 1;
        for (int i = 1; i < countOfNumbers; i++) {
            if (data[i] != last) {
                countOfUniq++;
                last = data[i];
            }
        }
        out.println(countOfUniq);
    }
    
    private static void sort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length);
    }

    private static void mergeSort(int[] array, int[] temp, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, temp, fromInclusive, mid);
        mergeSort(array, temp, mid, toExclusive);
        merge(array, temp, fromInclusive, mid, toExclusive);
    }

    private static void merge(int[] array, int[] temp, int fromInclusive, int mid, int toExclusive) {
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
