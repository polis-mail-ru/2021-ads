package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static void bubbleSort(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j][1] < array[j + 1][1]) {
                    int[][] temp = new int[1][2];
                    temp[0] = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp[0];
                } else if (array[j][1] == array[j + 1][1]) {
                    if (array[j][0] > array[j + 1][0]) {
                        int[][] temp = new int[1][2];
                        temp[0] = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp[0];
                    }
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] participants = new int[n][2];
        for (int[] participant : participants) {
            participant[0] = in.nextInt();
            participant[1] = in.nextInt();
        }

        bubbleSort(participants);

        for (int[] participant : participants) {
            out.println(participant[0] + " " + participant[1]);
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
