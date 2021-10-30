package ru.mail.polis.ads.part5.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9691378

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int w = in.nextInt(), h = in.nextInt(), n = in.nextInt();

        long max = Math.max(w, h);

        long l = max;
        long r = max * n;

        while (l < r) {
            long m = (l + r) / 2;

            long count = (m / w) * (m / h);
            if (count >= n) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        out.println(l);
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
