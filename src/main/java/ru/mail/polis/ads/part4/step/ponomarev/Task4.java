package ru.mail.polis.ads.part4.step.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9555018

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
         int k = in.nextInt() + 2;
        int[] cost = new int[k];
        int[] story = new int[k];

        for (int i = 1; i < k - 1; i++) {
            cost[i] = in.nextInt();
        }

        int n = in.nextInt();

        for (int i = 0; i < k; i++) {
            if (i >= 1) {
                story[i] = story[i - 1] + cost[i];
            }

            for (int h = 1; h <= n; h++) {
                if (h > i) {
                    break;
                }

                int newCost = story[i - h] + cost[i];

                if (newCost > story[i]) {
                    story[i] = newCost;
                }
            }
        }

        out.println(story[k - 1]);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
