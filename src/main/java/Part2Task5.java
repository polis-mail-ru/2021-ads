import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task5 {
    private Part2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        int[] firstArrayOfNumbers = new int[n1];
        for (int i = 0; i < n1; ++i) {
            firstArrayOfNumbers[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] secondArrayOfNumbers = new int[n2];
        for (int i = 0; i < n2; ++i) {
            secondArrayOfNumbers[i] = in.nextInt();
        }
        quickSort(firstArrayOfNumbers, 0, n1);
        quickSort(secondArrayOfNumbers, 0, n2);
        out.println(isHasEqualsNumber(firstArrayOfNumbers, secondArrayOfNumbers));
    }

    private static String isHasEqualsNumber(int[] array1, int[] array2) {
         int iInFirstArray = 0;
         int iInSecondArray = 0;
         while (iInFirstArray < array1.length || iInSecondArray < array2.length)  {
             if (iInFirstArray == array1.length || iInSecondArray == array2.length) {
                 return "NO";
             }
             while (iInFirstArray < array1.length - 1 && array1[iInFirstArray] == array1[iInFirstArray + 1]) {
                 iInFirstArray++;
             }
             while (iInSecondArray < array2.length - 1 && array2[iInSecondArray] == array2[iInSecondArray + 1]) {
                 iInSecondArray++;
             }
             if (array1[iInFirstArray] != array2[iInSecondArray]) {
                 return "NO";
             }
             iInFirstArray++;
             iInSecondArray++;
         }
         return "YES";
    }


    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static void swap(int[] array, int index, int indexNext) {
        int indexVariable = array[index];
        array[index] = array[indexNext];
        array[indexNext] = indexVariable;
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