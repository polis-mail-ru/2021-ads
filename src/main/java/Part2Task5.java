import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task5 {
    private Part2Task5() {
        // Should not be instantiated
    }

    private static void merge(int[] arr, int from, int mid, int to) {
        int[] temp = new int[to - from];
        int i = 0;
        int j = 0;

        while (from + i < mid && mid + j < to) {
            if (arr[from + i] < arr[mid + j]) {
                temp[i + j] = arr[from + i];
                i++;
            } else {
                temp[i + j] = arr[mid + j];
                j++;
            }
        }
        while (from + i < mid) {
            temp[i + j] = arr[from + i];
            i++;
        }
        while (mid + j < to) {
            temp[i + j] = arr[mid + j];
            j++;
        }

        for (i = 0; i < to - from; i++) {
            arr[from + i] = temp[i];
        }
    }

    private static void mergeSort(int[] arr, int from, int to) {
        if (from == to - 1) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        mergeSort(arr, from, mid);
        mergeSort(arr, mid, to);
        merge(arr, from, mid, to);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        n = in.nextInt();
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt();
        }
        mergeSort(arr1, 0, arr1.length);
        mergeSort(arr2, 0, arr2.length);

        int i = 0;
        int j = 0;
        boolean check = true;
        while (true) {
            if (arr1[i] != arr2[j]) {
                check = false;
                break;
            }
            if (i == arr1.length - 1 && j == arr2.length -1) {
                break;
            }
            while (i < arr1.length - 1 && arr1[i + 1] == arr1[i]) {
                i++;
            }
            while (j < arr2.length - 1 && arr2[j + 1] == arr2[j]) {
                j++;
            }
            if (i < arr1.length - 1) {
                i++;
            }
            if (j < arr2.length - 1) {
                j++;
            }
        }

        System.out.println(check ? "YES" : "NO");
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
