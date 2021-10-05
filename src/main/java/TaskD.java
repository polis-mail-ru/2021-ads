import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        if (size == 0) {
            out.println(0);
            return;
        }
        int[] inputArray = new int[size];
        for (int i = 0; i < size; i++) {
            inputArray[i] = in.nextInt();
        }
        sort(inputArray, 0, inputArray.length);
        int counter = 1;
        for (int i = 1; i < size; i++) {
            if (inputArray[i - 1] != inputArray[i]) {
                counter++;
            }
        }
        out.println(counter);
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
