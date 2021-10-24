package ru.mail.polis.ads.part5.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 3969. Дипломы
 * <p>
 * https://www.e-olymp.com/ru/submissions/9634968
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(solution(in.nextInt(), in.nextInt(), in.nextInt()));
    }

    /**
     * min{max(xw, yh) | x ∈ {1..n}, y ∈ {1..n}, xy ⩾ n}
     *
     * @param w w ⩽ h, w ∈ ℕ
     * @param h h ∈ ℕ
     * @param n n ∈ ℕ
     */
    private static long solution(long w, long h, long n) {
        if (w > h) {
            return solution(h, w, n);
        }
        double xStar = Math.sqrt((double) n * h / w);
        double yStar = Math.sqrt((double) n * w / h);
        return Math.min(
                h * Math.min(
                        ceilDiv(n, (long) Math.floor(xStar)),
                        ceilDiv((long) Math.ceil(xStar) * w, h)
                ), w * Math.min(
                        ceilDiv(n, (long) Math.floor(yStar)),
                        ceilDiv((long) Math.ceil(yStar) * h, w)
                )
        );
    }
    /**
     * ceilDiv(a, 0) = a + 1
     * ceilDiv(a, b) = ⌈a / b⌉
     *
     * @param a != 0
     * @param b >= 0
     */
    private static long ceilDiv(long a, long b) {
        if (b == 0) {
            return a + 1;
        }
        return (a - 1) / b + 1;
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
