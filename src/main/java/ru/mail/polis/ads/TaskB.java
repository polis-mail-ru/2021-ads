package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private static int[][] sumMap;
    private static int[][] map;

    private TaskB() {
        // Should not be instantiated
    }

    static int getSumMap(int m, int n) {
        if (m == 0 && n == 0) {
            sumMap[0][0] = map[0][0];
            return map[0][0];
        }
        if (m > map.length || m < 0 || n > map[0].length || n < 0) {
            throw new IndexOutOfBoundsException();
        }
        int sumLeft = -1;
        if (n - 1 >= 0) {
            if (sumMap[m][n - 1] == -1) {
                sumLeft = getSumMap(m, n - 1);
            } else {
                sumLeft = sumMap[m][n - 1];
            }
        }
        int sumDown = -1;
        if (m - 1 >= 0) {
            if (sumMap[m - 1][n] == -1) {
                sumDown = getSumMap(m - 1, n);
            } else {
                sumLeft = sumMap[m - 1][n];
            }
        }
        int newSum = Math.max(sumDown, sumLeft) + map[m][n];
        if (newSum > sumMap[m][n]) {
            sumMap[m][n] = newSum;
        }
        return sumMap[m][n];
    }

    static String getPath(StringBuilder stringBuilder, int m, int n) {
        if(m ==0 && n==0){
            return stringBuilder.reverse().toString();
        }
        if(m == 0) {
            stringBuilder.append('R');
            return getPath(stringBuilder, m, n-1);
        }
        if(n==0) {
            stringBuilder.append('F');
            return getPath(stringBuilder, m-1, n);
        }
        if(getSumMap(m-1,n) > getSumMap(m,n-1)) {
            stringBuilder.append('F');
            return getPath(stringBuilder, m-1, n);
        } else {
            stringBuilder.append('R');
            return getPath(stringBuilder, m, n-1);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        map = new int[m][n];
        sumMap = new int[m][n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                map[i][j] = in.nextInt();
                sumMap[i][j] = -1;
            }
        }
        getSumMap(m-1,n-1);
        System.out.println(getPath(new StringBuilder(), m-1, n-1));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
