import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Week2Task2 {
    private Week2Task2() {
        // Should not be instantiated
    }

    private static final int MAX_MEMORY = 107;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNumbers = in.nextInt();
        int minNum = 2_000_000_001;//max number by condition
        int[] data = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            data[i] = in.nextInt();
            if (data[i] < minNum) {
                minNum = data[i];
            }
        }
        sort(data, minNum);
        for (int num : data) {
            out.print(num + " ");
        }
    }

    private static void sort(int[] array, int offset) {
        int[] temp = new int[MAX_MEMORY];
        for (int num : array) {
            temp[num - offset]++;
        }
        int i = 0;
        for (int j = 0; j < MAX_MEMORY; j++) {
            if (temp[j] != 0) {
                for (int k = 1; k <= temp[j]; k++) {
                    array[i] = j + offset;
                    i++;
                }
            }
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
