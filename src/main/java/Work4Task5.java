import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Work4Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        out.println(countInv(array, 0, n));
    }

    private static int countInv(int[] a, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = (i + j) / 2;
        return countInv(a, i, mid) + countInv(a, mid, j) + countSplitInv(a, i, j, mid);
    }

    private static int countSplitInv(int[] array, int i, int j, int mid) {
        int[] left = new int[mid - i];
        int[] right = new int[j - mid];
        System.arraycopy(array, i, left, 0, mid - i);
        System.arraycopy(array, mid, right, 0, j - mid);

        int res = 0, leftIndex = 0, rightIndex = 0;
        int count = 0;
        for (int k = i; k < j; k++) {
            if (leftIndex >= mid - i) {
                array[k] = right[rightIndex++];
            } else if (rightIndex >= j - mid) {
                array[k] = left[leftIndex++];
                res += count;
            } else if (left[leftIndex] < right[rightIndex]) {
                array[k] = left[leftIndex++];
                res += count;
            } else {
                array[k] = right[rightIndex++];
                count++;
            }
        }

        return res;
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
