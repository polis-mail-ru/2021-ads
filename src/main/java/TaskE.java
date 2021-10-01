import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N1 = in.nextInt();
        int[] array1 = new int[N1];
        for (int i = 0; i < N1; i++) {
            array1[i] = in.nextInt();
        }
        int N2 = in.nextInt();
        int[] array2 = new int[N2];
        for (int i = 0; i < N2; i++) {
            array2[i] = in.nextInt();
        }
        sort(array1, 0, N1);
        sort(array2, 0, N2);
        int i = 0;
        int j = 0;
        while (i < N1 || j < N2) {
            if ((i >= N1) || (j >= N2) || (array1[i] != array2[j])) {
                out.println("NO");
                return;
            }
            do {
                i++;
            } while ((i < N1) && (array1[i - 1] == array1[i]));
            do {
                j++;
            } while ((j < N2) && (array2[j - 1] == array2[j]));
        }
        out.println("YES");
    }

    private static void sort(int[] array, int from, int to) {
        if (from >= to - 1) {
            return;
        }
        int i = partition(array, from, to);
        sort(array, from, i);
        sort(array, i + 1, to);
    }

    private static int partition(int[] array, int from, int to) {
        int pivotal = array[from];
        int i = from;
        for (int j = from + 1; j < to; j++) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, from);
        return i;
    }

    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
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
