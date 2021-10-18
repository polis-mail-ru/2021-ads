package ru.mail.polis.ads.part4.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9576804
public class Task2 {
    private static final int START_COORDINATE = 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int m = in.nextInt();
        final int n = in.nextInt();
        int[][] matrix = new int[m + 1][n + 1];
        inputReverseMatrix(in, m, n, matrix);
        buildDynamicTable(m, n, matrix);
        char[] reverseAnswer = new char[m + n - 2];
        findReverseAnswer(matrix, m, n, reverseAnswer);
        for (int k = reverseAnswer.length - 1; k >= 0; k--) {
            out.print(reverseAnswer[k]);
        }
    }

    /**
     * Ввод матрицы в "перевернутом виде"
     */
    private static void inputReverseMatrix(FastScanner in, int m, int n, int[][] matrix) {
        for (int i = START_COORDINATE; i <= m; i++) {
            for (int j = START_COORDINATE; j <= n; j++) {
                matrix[m - i + START_COORDINATE][j] = in.nextInt();
            }
        }
    }

    /**
     * Составление таблицы дп
     */
    private static void buildDynamicTable(int m, int n, int[][] matrix) {
        for (int i = START_COORDINATE; i <= m; i++) {
            for (int j = START_COORDINATE; j <= n; j++) {
                matrix[i][j] += Math.max(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }
    }

    /**
     * Получение ответа с конца
     */
    private static void findReverseAnswer(int[][] matrix, int m, int n, char[] ans) {
        int i = m;
        int j = n;
        for (int top = 0; i != START_COORDINATE || j != START_COORDINATE; top++) {
            if (i == START_COORDINATE) {
                j--;
                ans[top] = 'R';
            } else if (j == START_COORDINATE) {
                i--;
                ans[top] = 'F';
            } else if (matrix[i][j - 1] >= matrix[i - 1][j]) {
                j--;
                ans[top] = 'R';
            } else {
                i--;
                ans[top] = 'F';
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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