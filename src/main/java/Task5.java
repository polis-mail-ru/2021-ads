import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int number1 = in.nextInt();
        int[] arrayA = new int[number1];
        for (int i = 0; i < number1; i++) {
            arrayA[i] = in.nextInt();
        }

        int number2 = in.nextInt();
        int[] arrayB = new int[number2];
        for (int j = 0; j < number2; j++) {
            arrayB[j] = in.nextInt();
        }

        quickSort(arrayA, 0, number1 - 1);
        quickSort(arrayB, 0, number2 - 1);

        int i = 0;
        int j = 0;
        int currentA = 0;
        int currentB = 0;

        while ((i < number1) && (j < number2)) {
            currentA = arrayA[i];
            currentB = arrayB[j];
            if (currentA != currentB) {
                break;
            }
            while ((i < number1) && (arrayA[i] == currentA)) {
                i++;
            }
            while ((j < number2) && (arrayB[j] == currentB)) {
                j++;
            }
        }

        if ((i != number1) || (j != number2)) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }

    private static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
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
