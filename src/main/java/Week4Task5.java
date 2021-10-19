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
        out.println(countInv(A, 0, n - 1));
    }

    private static int countSplitInv(int[] arr, int l, int m, int r) {
        int length = r - l + 1;
        int[] buffer = new int[length];
        int i = 0;
        int j = l;
        int k = m;
        int count = 0;
        while (j <= m - 1 && k <= r) {
            if (arr[j] <= arr[k]) {
                buffer[i++] = arr[j++];
            } else {
                buffer[i++] = arr[k++];
                count += m - j;
            }
        }
        while (j <= m - 1) {
            buffer[i++] = arr[j++];
        }
        while (k <= r) {
            buffer[i++] = arr[k++];
        }
        i = 0;
        while (l <= r) {
            arr[l++] = buffer[i++];
        }
        return count;
    }

    private static int countInv(int[] arr, int l, int r) {
        if (r - l <= 0) {
            return 0;
        }
        int m = (l + r) / 2;
        return countInv(arr, l, m) + countInv(arr, m + 1, r) + countSplitInv(arr, l, m + 1, r);
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
