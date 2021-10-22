package tkachenkoalexandra;

import java.io.*;
import java.util.StringTokenizer;

public final class SecondTask {

    private static final int MAX_SIZE = 100;
    private static final int MAX_COUNT = 30_000;

    private SecondTask() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = checkCount(in.nextInt(), MAX_SIZE);
        int cols = checkCount(in.nextInt(), MAX_SIZE);
        int[][] array = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; ++i) {
            for (int j = 1; j < cols + 1; ++j) {
                array[i][j] = checkCount(in.nextInt(), MAX_COUNT);
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 1; j < cols + 1; j++) {
                array[i][j] += Math.max(array[i + 1][j], array[i][j - 1]);
            }
        }
        StringBuilder path = new StringBuilder();
        int i = 0;
        int j = cols;
        while (i < rows + 1 && j > 1) {
            if (array[i + 1][j] > array[i][j - 1]) {
                path.append('F');
                i++;
            } else {
                path.append('R');
                j--;
            }
        }
        for (; j > 1; j--) {
            path.append('R');
        }
        for (; i < rows - 1; i++) {
            path.append('F');
        }
        out.println(path.reverse());

    }

    private static int checkCount(int n, int LIMIT) {
        if ((n < 0) || (n > LIMIT)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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

