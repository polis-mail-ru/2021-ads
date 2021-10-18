import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9549897
public class Inversions {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(countInversions(a, 0, n - 1));
    }

    private static int mergeAndCount(int[] a, int l, int mid, int r) {
        if (l >= r) {
            return 0;
        }
        int[] left = Arrays.copyOfRange(a, l, mid + 1);
        int[] right = Arrays.copyOfRange(a, mid + 1, r + 1);
        int i = 0;
        int j = 0;
        int k = l;
        int inv = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else {
                a[k++] = right[j++];
                inv += mid - (l + i) + 1;
            }
        }
        while (i < left.length)
            a[k++] = left[i++];
        while (j < right.length)
            a[k++] = right[j++];
        return inv;
    }

    private static int countInversions(int[] a, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) / 2;
        int leftInversions = countInversions(a, l, mid);
        int rightInversions = countInversions(a, mid + 1, r);
        int mergeInversions = mergeAndCount(a, l, mid, r);
        return leftInversions + rightInversions + mergeInversions;
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
