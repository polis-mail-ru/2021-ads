import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task4 {
    private Part2Task4() {
        // Should not be instantiated
    }

    static int[] bufferedArray;

    private static void merge(int[] arr, int from, int mid, int to, int[] bufferedArray) {
        int i = 0;
        int j = 0;

        while (from + i < mid && mid + j < to) {
            if (arr[from + i] < arr[mid + j]) {
                bufferedArray[i + j] = arr[from + i];
                i++;
            } else {
                bufferedArray[i + j] = arr[mid + j];
                j++;
            }
        }
        while (from + i < mid) {
            bufferedArray[i + j] = arr[from + i];
            i++;
        }
        while (mid + j < to) {
            bufferedArray[i + j] = arr[mid + j];
            j++;
        }

        for (i = 0; i < to - from; i++) {
            arr[from + i] = bufferedArray[i];
        }
    }

    private static void mergeSort(int[] arr, int from, int to, int[] bufferedArray) {
        if (from == to - 1) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        mergeSort(arr, from, mid, bufferedArray);
        mergeSort(arr, mid, to, bufferedArray);
        merge(arr, from, mid, to, bufferedArray);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] bufferedArray = new int[n];
        mergeSort(arr, 0, n, bufferedArray);
        int count = 1;
        int last = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] != last) {
                count++;
                last = arr[i];
            }
        }
        System.out.println(count);
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
