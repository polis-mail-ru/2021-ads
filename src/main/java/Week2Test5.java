

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
public final class Week2Test5 {
    private Week2Test5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array1 = new int[in.nextInt()];

        for (int i = 0; i < array1.length; ++i) {
            array1[i] = in.nextInt();
        }

        int[] array2 = new int[in.nextInt()];

        for (int i = 0; i < array2.length; ++i) {
            array2[i] = in.nextInt();
        }

        qsort(array1, 0, array1.length - 1);
        qsort(array2, 0, array2.length - 1);

        int counter1 = 1;
        for (int i = 1; i < array1.length; ++i) {
            if (array1[i] != array1[i - 1]) {
                ++counter1;
            }
        }

        int[] temp1 = new int[counter1];
        temp1[0] = array1[0];
        counter1 = 1;
        for (int i = 1; i < array1.length; ++i) {
            if (array1[i] != array1[i - 1]) {
                temp1[counter1] = array1[i];
                ++counter1;
            }
        }

        int counter2 = 1;
        for (int i = 1; i < array2.length; ++i) {
            if (array2[i] != array2[i - 1]) {
                ++counter2;
            }
        }

        int[] temp2 = new int[counter2];
        temp2[0] = array2[0];
        counter2 = 1;
        for (int i = 1; i < array2.length; ++i) {
            if (array2[i] != array2[i - 1]) {
                temp2[counter2] = array2[i];
                ++counter2;
            }
        }

        if (temp1.length != temp2.length) {
            out.println("NO");
            return;
        }
        counter1 = 0;
        while (counter1 < temp1.length) {
            if (temp1[counter1] != temp2[counter1]) {
                out.println("NO");
                return;
            }
            ++counter1;
        }

        out.println("YES");
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
