import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private static int[] arr;

    private TaskE() {
        // Should not be instantiated
    }

    private static int countInversions(int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int middle = (i + j) / 2;
        return countInversions(i, middle) + countInversions(middle, j) + countSplitInversions(i, j, middle);
    }

    private static int countSplitInversions(int start, int end, int middle) {
        int counter = 0;
        int length = end - start;
        int lI = start;
        int rI = middle;
        int[] swap = new int[length];
        for (int i = 0; i < length; i++) {
            if (lI == middle) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[rI++];
                }
                break;
            } else if (rI == end) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[lI++];
                }
                break;
            }
            if (arr[lI] < arr[rI]) {
                swap[i] = arr[lI++];
            } else {
                counter += middle - lI;
                swap[i] = arr[rI++];
            }
        }
        for (int i = start; i < end; i++) {
            arr[i] = swap[i - start];
        }
        return counter;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(countInversions(0, arr.length));
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
