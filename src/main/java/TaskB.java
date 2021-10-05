import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = in.nextInt();
        }
        radixSort(numbers);
        for (int i = 0; i < size; i++) {
            out.println(numbers[i]);
        }
    }

    private static void radixSort(int[] in) {
        List<Integer>[] arrays = new ArrayList[10];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = new ArrayList<>();
        }
        int divider = 1;
        int currentNumber;
        for (int i = 0; i < 4; i++) {
            for (Integer number: in) {
                currentNumber = number / divider;
                arrays[currentNumber % 10].add(number);
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer number: arrays[j]) {
                    in[index] = number;
                    index++;
                }
                arrays[j].clear();
            }
            divider *= 10;
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
