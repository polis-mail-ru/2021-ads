import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork2Task5 {
    private HomeWork2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        long[] array1 = new long[n1];
        for (int i = 0; i < n1; i++) {
            array1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        long[] array2 = new long[n2];
        for (int i = 0; i < n2; i++) {
            array2[i] = in.nextInt();
        }
        sort(array1, 0, n1 - 1);
        sort(array2, 0, n2 - 1);
        if (n1 <= n2) {
            out.println(isSimilar(array1, array2));
        } else {
            out.println(isSimilar(array2, array1));
        }
    }

    private static String isSimilar(long[] shortArray, long[] longArray) {
        if (shortArray.length > longArray.length) {
            long[] tempArray = new long[shortArray.length];
            shortArray = longArray;
            longArray = tempArray;
        }
        int n1 = shortArray.length;
        int n2 = longArray.length;
        int indexLongArray = 0;
        for (int i = 0; i < n1 - 1; i++) {
            if (shortArray[i] == shortArray[i + 1]) {
                continue;
            }
            if (shortArray[i] != longArray[indexLongArray]) {
                return "NO";
            }
            while (indexLongArray < n2 - 1 && longArray[indexLongArray] == longArray[indexLongArray + 1]) {
                ++indexLongArray;
            }
            ++indexLongArray;
        }
        if (shortArray[n1 - 1] == longArray[n2 - 1]) {
            return "YES";
        }
        return "NO";
    }


    static void sort(long[] arr, int fromInclusive, int toInclusive) {
        if (fromInclusive < toInclusive) {
            int mid = (fromInclusive + toInclusive) / 2;
            sort(arr, fromInclusive, mid);
            sort(arr, mid + 1, toInclusive);
            merge(arr, fromInclusive, mid, toInclusive);
        }
    }

    static void merge(long[] array, int fromInclusive, int mid, int toInclusive) {
        int l = mid - fromInclusive + 1;
        int r = toInclusive - mid;
        long[] leftArray = new long[l];
        long[] rightArray = new long[r];
        for (int i = 0; i < l; ++i) {
            leftArray[i] = array[fromInclusive + i];
        }
        for (int j = 0; j < r; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = fromInclusive;
        while (i < l && j < r) {
            if (leftArray[i] < rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < r) {
            array[k] = rightArray[j];
            j++;
            k++;
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