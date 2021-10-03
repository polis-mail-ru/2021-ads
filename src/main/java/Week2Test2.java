

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Test2 {
    private Week2Test2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];
        
        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
        }
        
        sort(array);
        
        for (int i = 0; i < array.length; ++i) {
            out.print(array[i] + " ");
        }
    }

    private static void sort(int[] array) {
        int min;
        int max;
        max = min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] temp = new int[max - min + 1];

        for (int i = 0; i < array.length; ++i) {
            temp[array[i] - min]++;
        }

        int idx = 0;

        for (int i = 0; i < temp.length; ++i) {
            for (int j = 0; j < temp[i]; ++j) {
                array[idx++] = i + min;
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
