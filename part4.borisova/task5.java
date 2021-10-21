import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task5 {
    private static int count = 0;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        count = SortUnsorted(array, 0, array.length - 1);
        out.println(count);
        //out.print(Arrays.toString(array));
    }

    private static int SortUnsorted(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        SortUnsorted(a, lo, mid);
        SortUnsorted(a, mid + 1, hi);
        int[] buf = Arrays.copyOfRange(a, lo, hi + 1);

        for (int k = lo; k <= hi; k++) {
            buf[k - lo] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) { // если первую половину целиком вставили
                a[k] = buf[j - lo];
                j++;

            } else if (j > hi) { // если вторую половину целиком вставили
                a[k] = buf[i - lo];
                count += j - mid - 1;
                i++;

            } else if (buf[j - lo] < buf[i - lo]) { // если элемент из второй части меньше элемента из первой части
                a[k] = buf[j - lo];
                j++;

            } else { // если элемент из первой части больше элемента из второй части
                a[k] = buf[i - lo];
                count += j - mid - 1;
                i++;
            }
        }
        return count;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}