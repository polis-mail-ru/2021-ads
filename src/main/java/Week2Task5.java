import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task5 {
    private Week2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNumbers1 = in.nextInt();
        int[] data1 = new int[countOfNumbers1];
        for (int i = 0; i < countOfNumbers1; i++) {
            data1[i] = in.nextInt();
        }
        sort(data1);
        int index1 = 1;
        int last1 = data1[0];

        int countOfNumbers2 = in.nextInt();
        int[] data2 = new int[countOfNumbers2];
        for (int i = 0; i < countOfNumbers2; i++) {
            data2[i] = in.nextInt();
        }
        sort(data2);
        int index2 = 1;
        int last2 = data2[0];

        while (index1 < countOfNumbers1 || index2 < countOfNumbers2) {
            while (index1 < countOfNumbers1 && last1 == data1[index1]) {
                index1++;
            }
            while (index2 < countOfNumbers2 && last2 == data2[index2]) {
                index2++;
            }
            if (last1 != last2) {
                out.println("NO");
                return;
            }
            if (index1 < countOfNumbers1) {
                last1 = data1[index1];
            }
            if (index2 < countOfNumbers2) {
                last2 = data2[index2];
            }
        }
        out.println("YES");
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
