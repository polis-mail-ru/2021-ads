package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int[] swap = new int[arr.length];

        int middle = arr.length / 2;
        subMergeSort(arr, swap, 0, middle);
        subMergeSort(arr, swap, middle, arr.length);
        merge(arr, swap, 0, middle, arr.length);
    }

    private static void subMergeSort(int[] arr, int[] swap, int start, int end) {
        if (end - start == 1) {
            return;
        }
        int middle = start + ((end - start) / 2);

        subMergeSort(arr, swap, start, middle);
        subMergeSort(arr, swap, middle, end);

        merge(arr, swap, start, middle, end);
    }

    private static void merge(int[] arr, int[] swap, int start, int middle, int end) {
        int length = end - start;
        int lI = start;
        int rI = middle;
        for (int i = 0; i < length; i++) {
            if (lI == middle) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[rI++];
                }
                break;
            } else if (rI == end) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[lI++];
                }
                break;
            }
            if (arr[lI] < arr[rI]) {
                swap[i] = arr[lI++];
            } else {
                swap[i] = arr[rI++];
            }
        }
        for (int i = start; i < end; i++) {
            arr[i] = swap[i - start];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        mergeSort(arr);
        int counter = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                counter++;
            }
        }
        out.println(counter);
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
}
