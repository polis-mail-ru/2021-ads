import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public final class HomeWork4Task1 {

    private static int[] tempArray;

    private HomeWork4Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        tempArray = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.println(invCount(array, 0, n - 1));
    }

    static int merge(int[] array, int fromInclusive, int mid, int toInclusive) {
        int counter = 0;
        int l = mid - fromInclusive + 1;
        int r = toInclusive - mid;
        for (int i = 0; i < l; ++i) {
            tempArray[i] = array[fromInclusive + i];
        }
        int i = 0;
        int j = 0;
        int k = fromInclusive;
        while (i < l && j < r) {
            if (tempArray[i] < array[mid + 1 + j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = array[mid + 1 + j];
                j++;
                counter += l - i;
            }
            k++;
        }
        while (i < l) {
            array[k] = tempArray[i];
            i++;
            k++;
        }
        while (j < r) {
            array[k] = array[mid + 1 + j];
            j++;
            k++;
        }
        return counter;
    }

    static int invCount(int[] array, int fromInclusive, int toInclusive) {
        if (fromInclusive >= toInclusive) {
            return 0;
        }
        int mid = (fromInclusive + toInclusive) / 2;
        return invCount(array, fromInclusive, mid)
                + invCount(array, mid + 1, toInclusive)
                + merge(array, fromInclusive, mid, toInclusive);
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