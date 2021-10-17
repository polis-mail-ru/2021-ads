import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private static int[][] arr;
    private static String brackets;

    private TaskA() {
        // Should not be instantiated
    }

    private static String getValue(int i, int j) {
        if (i > j) {
            return "";
        } else if (i == j) {
            if (brackets.charAt(i) == '(' || brackets.charAt(i) == ')') {
                return "()";
            } else {
                return "[]";
            }
        }
        int min = Integer.MAX_VALUE;
        int iMin = -1;
        for (int k = i; k < j; k++) {
            int temp = arr[i][k] + arr[k + 1][j];
            if (min > temp) {
                min = temp;
                iMin = k;
            }
        }
        if ((brackets.charAt(i) == '(' && brackets.charAt(j) == ')')
                || (brackets.charAt(i) == '[' && brackets.charAt(j) == ']')) {
            if (min >= arr[i + 1][j - 1]) {
                arr[i][j] = arr[i + 1][j - 1];
                if (brackets.charAt(i) == '(') {
                    return "(" + getValue(i + 1, j - 1) + ")";
                } else {
                    return "[" + getValue(i + 1, j - 1) + "]";
                }
            }
        }
        return getValue(i, iMin) + getValue(iMin + 1, j);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner inner = new Scanner(System.in);
        brackets = inner.nextLine();
        int size = brackets.length();
        if (size == 0) {
            return;
        }
        arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
        }

        for (int shift = 1; shift < size; shift++) {
            for (int i = 0; i < size - shift; i++) {
                int j = i + shift;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = arr[i][k] + arr[k + 1][j];
                    if (min > temp) {
                        min = temp;
                    }
                }
                arr[i][j] = min;
                if ((brackets.charAt(i) == '(' && brackets.charAt(j) == ')')
                        || (brackets.charAt(i) == '[' && brackets.charAt(j) == ']')) {
                    if (min >= arr[i + 1][j - 1]) {
                        arr[i][j] = arr[i + 1][j - 1];
                    }
                }
            }
        }
        out.println(getValue(0, size - 1));
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
