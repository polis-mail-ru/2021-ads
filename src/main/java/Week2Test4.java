

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
public final class Week2Test4 {
    private Week2Test4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];
        
        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
        }
        
        qsort(array, 0, array.length - 1);
        
        int counter = 1;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] != array[i - 1]) {
                ++counter;
            }
        }
        
        out.println(counter);
    }

    private static void qsort(int[] array, int start, int end) {
        if (start < end) {
            int pivot = array[end];
            int i = start - 1;
            int temp;
            
            for (int j = start; j <= end; j++) {
                if (array[j] < pivot) {
                    i++;
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            
            temp = array[i + 1];
            array[i + 1] = array[end];
            array[end] = temp;
            qsort(array, start, i);
            qsort(array, i + 2, end);
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
