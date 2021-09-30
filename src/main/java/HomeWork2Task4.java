import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork2Task4 {
    private HomeWork2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        /* Можно так:
        int n = in.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(in.nextInt());
        }
        out.println(set.size()); */

        // Но если Set нельзя, то так:
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        sort(array, 0, n - 1);
        int counter = 0;
        for (int i = 0; i < n - 1; i++) {
            if (array[i] == array[i + 1]) {
                ++counter;
            }
        }
        out.println(n - counter);
    }

    static void sort(int[] arr, int fromInclusive, int toInclusive) {
        if (fromInclusive < toInclusive) {
            int mid = (fromInclusive + toInclusive) / 2;
            sort(arr, fromInclusive, mid);
            sort(arr, mid + 1, toInclusive);
            merge(arr, fromInclusive, mid, toInclusive);
        }
    }

    static void merge(int[] array, int fromInclusive, int mid, int toInclusive) {
        int l = mid - fromInclusive + 1;
        int r = toInclusive - mid;
        int[] leftArray = new int[l];
        int[] rightArray = new int[r];
        for (int i = 0; i < l; ++i) {
            leftArray[i] = array[fromInclusive + i];
        }
        for (int j = 0; j < r; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = fromInclusive;
        while (i < l && j < r) {
            if (leftArray[i] < rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < r) {
            array[k] = rightArray[j];
            j++;
            k++;
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