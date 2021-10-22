import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week5Task5 {
    private Week5Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        out.println(PrintPermutation(arr));
        while (NextPermutate(arr)) {
            out.println(PrintPermutation(arr));
        }
    }

    private static boolean NextPermutate(int[] arr) {
        int end = arr.length - 1;
        int i = end;
        int j = end;
        while (i > 0 && arr[i] <= arr[i - 1]) {
            i--;
        }
        if (i == 0) {
            return false;
        } else {
            while (j > 0 && arr[j] <= arr[i - 1]) {
                j--;
            }
            Swap(i - 1, j, arr);
            Reverse(i, end, arr);
            return true;
        }
    }

    private static void Swap(int first, int second, int[] arr) {
        int buffer = arr[first];
        arr[first] = arr[second];
        arr[second] = buffer;
    }

    private static void Reverse(int first, int second, int[] arr) {
        for (int i = first, j = second; i < j; i++, j--) {
            Swap(i, j, arr);
        }
    }

    private static String PrintPermutation(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int j : arr) {
            result.append(j).append(" ");
        }
        return result.toString();
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
