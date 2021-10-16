import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week4Task5 {
    private Week4Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        out.println(countInv(A));
    }

    private static int countSplitInv(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                count += left.length - i;
                j++;
            }
        }
        return count;
    }

    private static int countInv(int[] arr) {
        if (arr.length < 2)
            return 0;
        int m = (arr.length + 1) / 2;
        int[] left = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = arr[i];
        }
        int[] right = new int[arr.length - m];
        for (int i = 0; i < arr.length - m; i++) {
            right[i] = arr[m + i];
        }
        return countInv(left) + countInv(right) + countSplitInv(arr, left, right);
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
