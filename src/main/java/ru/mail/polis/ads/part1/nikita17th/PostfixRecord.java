package ru.mail.polis.ads.part1.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;


public final class PostfixRecord {
    private PostfixRecord() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Long> stack = new LinkedList<>();
        String input;
        long firstOperand;
        long secondOperand;
        while (in.hasNext()) {
            input = in.next();
            switch (input) {
                case "+":
                    stack.add(stack.removeLast() + stack.removeLast());
                    break;
                case "-":
                    secondOperand = stack.removeLast();
                    firstOperand = stack.removeLast();
                    stack.add(firstOperand - secondOperand);
                    break;
                case "*":
                    stack.add(stack.removeLast() *stack.removeLast());
                    break;
                default:
                    stack.add(Long.parseLong(input));
                    break;
            }
        }
        out.println(stack.removeLast());
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
            return tokenizer == null || tokenizer.hasMoreTokens();
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
