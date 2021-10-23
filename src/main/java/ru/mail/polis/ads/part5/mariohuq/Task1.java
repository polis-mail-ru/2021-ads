package ru.mail.polis.ads.part5.mariohuq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.function.DoubleUnaryOperator;

/**
 * 3968. Квадратный корень
 * <p>
 * https://www.e-olymp.com/ru/submissions/9626769
 */
public final class Task1 {
    private static final double EPSILON = 1e-6;
    // ceiling of positive solution of x^2 + sqrt(x) = 10^10
    public static final double MAX_T = 1e5;
    public static final double MIN_T = 0;

    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double x = solution(MIN_T, MAX_T, t -> t * t + Math.sqrt(t), c);
        out.println(x);
    }

    /**
     * Preconditions:
     *     maxT >= minT
     *     for all t1, t2 from [minT; maxT]:
     *         if t1 <= t2 then leftHandSide.applyAsDouble(t1) <= leftHandSide.applyAsDouble(t2)
     *         (in other words, leftHandSide is non-decreasing on [minT; maxT])
     *     leftHandSide.applyAsDouble(minT) <= rightHandSide
     *     leftHandSide.applyAsDouble(maxT) >= rightHandSide
     */
    private static double solution(double minT, double maxT, DoubleUnaryOperator leftHandSide, double rightHandSide) {
        double midT = (maxT + minT) / 2;
        if (maxT - minT <= EPSILON) {
            return midT;
        }
        int compare = Double.compare(leftHandSide.applyAsDouble(midT), rightHandSide);
        if (compare == 0) {
            return midT;
        }
        if (compare > 0) {
            return solution(minT, midT, leftHandSide, rightHandSide);
        }
        return solution(midT, maxT, leftHandSide, rightHandSide);
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

        double nextDouble() {
            return Double.parseDouble(next());
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
