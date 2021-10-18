package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int maxSeq = 0;
                if (i > 0) {
                    maxSeq = d[i-1][j];
                }
                if (j > 0 && d[i][j-1] > maxSeq) {
                    maxSeq = d[i][j-1];
                }
                if (a[i] == b[j]) {
                    if(i==0 || j==0) {
                        maxSeq++;
                    } else {
                        maxSeq = 1 + d[i-1][j-1];
                    }
                }
                d[i][j] = maxSeq;
            }
        }
        System.out.println(d[n - 1][m - 1]);
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
