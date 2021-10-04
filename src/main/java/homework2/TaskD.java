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
        if (arr.length == 1) {
            return;
        }
        int middle = arr.length / 2;

        int[] l = new int[middle];
        int[] r = new int[arr.length - middle];

        System.arraycopy(arr, 0, l, 0, l.length);
        System.arraycopy(arr, middle, r, 0, r.length);
        mergeSort(l);
        mergeSort(r);

        merge(arr, l, r);
    }

    private static void merge(int[] arr, int[] l, int[] r) {
        int lI = 0;
        int rI = 0;
        for (int i = 0; i < arr.length; i++) {
            if (lI == l.length) {
                for (int j = rI; j < r.length; j++) {
                    arr[i++] = r[rI++];
                }
                break;
            } else if (rI == r.length) {
                for (int j = lI; j < l.length; j++) {
                    arr[i++] = l[lI++];
                }
                break;
            }
            if (l[lI] > r[rI]) {
                arr[i] = r[rI++];
            } else {
                arr[i] = l[lI++];
            }
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
