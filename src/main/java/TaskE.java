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
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeOfFirst = in.nextInt();
        int[] firstArray = new int[sizeOfFirst];
        for (int i = 0; i < sizeOfFirst; i++) {
            firstArray[i] = in.nextInt();
        }
        int sizeOfSecond = in.nextInt();
        int[] secondArray = new int[sizeOfSecond];
        for (int i = 0; i < sizeOfSecond; i++) {
            secondArray[i] = in.nextInt();
        }
        sort(firstArray, 0 , sizeOfFirst);
        sort(secondArray, 0, sizeOfSecond);
        int i = 0;
        int j = 0;
        while (i < sizeOfFirst && j < sizeOfSecond) {
            if (firstArray[i] != secondArray[j]) {
                out.println("NO");
                return;
            }
            while ((i + 1 < sizeOfFirst) && firstArray[i] == firstArray[i + 1]) {
                i++;
            }
            while ((j + 1 < sizeOfSecond) && secondArray[j] == secondArray[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        if (i == sizeOfFirst && j == sizeOfSecond) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static void sort(int[] in, int left, int right) {
        if (left >= right - 1) {
            return;
        }
        int i = partition(in, left, right);
        sort(in, left, i);
        sort(in, i + 1, right);
    }

    private static int partition(int[] in, int left, int right) {
        int pivotal = in[left];
        int i = left;
        for (int j = left + 1; j < right; j++) {
            if (in[j] <= pivotal) {
                swap(in, ++i, j);
            }
        }
        swap(in, i, left);
        return i;
    }

    private static void swap(int[] in, int left, int right) {
        int tmp = in[left];
        in[left] = in[right];
        in[right] = tmp;
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
