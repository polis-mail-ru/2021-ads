import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task5 {
    private Week2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] firstArray = new int[in.nextInt()];
        for (int i = 0; i < firstArray.length; ++i) {
            firstArray[i] = in.nextInt();
        }

        int[] secondArray = new int[in.nextInt()];
        for (int i = 0; i < secondArray.length; ++i) {
            secondArray[i] = in.nextInt();
        }

        sort(firstArray, 0, firstArray.length);
        sort(secondArray, 0, secondArray.length);

        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < firstArray.length && secondIndex < secondArray.length) {
            if (firstArray[firstIndex] != secondArray[secondIndex]) {
                out.println("NO");
                return;
            }

            firstIndex++;
            secondIndex++;

            while (firstIndex < firstArray.length && firstArray[firstIndex - 1] == firstArray[firstIndex]) {
                firstIndex++;
            }
            while (secondIndex < secondArray.length && secondArray[secondIndex - 1] == secondArray[secondIndex]) {
                secondIndex++;
            }
        }

        out.println(firstIndex < firstArray.length || secondIndex < secondArray.length ? "NO" : "YES");
    }

    private static void sort(int[] arr, int l, int r) {
        if (l == r - 1) {
            return;
        }

        int mid = (r + l) / 2;
        sort(arr, l, mid);
        sort(arr, mid, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int j = left;
        int k = mid;

        int[] temp = new int[right - left];
        for (int i = 0; i < right - left; ++i) {
            if (j < mid && k < right) {
                if (arr[j] < arr[k]) {
                    temp[i] = arr[j];
                    ++j;
                } else {
                    temp[i] = arr[k];
                    ++k;
                }
            } else if (j < mid) {
                temp[i] = arr[j];
                ++j;
            } else if (k < right) {
                temp[i] = arr[k];
                ++k;
            }
        }

        for (int i = 0; i < right - left; ++i) {
            arr[i + left] = temp[i];
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
