package ru.mail.polis.ads.part5.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int width = in.nextInt();
        int height = in.nextInt();
        int n = in.nextInt();
        long left = Math.min(width, height);
        long right = (long) Math.max(width, height) * n;
        long mid = (left + right) / 2;
        while (left < right) {
            long cond = (mid / width) * (mid / height);
            if (cond >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        out.println(left);
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
