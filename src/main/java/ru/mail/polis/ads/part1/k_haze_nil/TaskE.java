package ru.mail.polis.ads.part1.k_haze_nil;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */


public final class TaskE {


    private TaskE() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        String token;
        boolean lastOne = true;

        while (in.hasNext() || lastOne) {
            token = in.next();

            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            Integer second = stack.pop();
            Integer first = stack.pop();

            switch (token) {
                case "*":
                    stack.push(first * second);
                    break;
                case "+":
                    stack.push(first + second);
                    break;
                case "-":
                    stack.push(first - second);
                    break;
            }

            if (!in.hasNext()) {
                lastOne = false;
            }


        }

        out.println(stack.pop());

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

        boolean hasNext() {
            return tokenizer != null && tokenizer.hasMoreTokens();
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
