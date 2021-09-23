package ru.mail.polis.ads.part1.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;


public final class RightBracketSequence {
    private RightBracketSequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Character> stack = new LinkedList<>();
        String input = in.next();
        char chekBracket;
        char[] brackets = input.toCharArray();
        for (char ch : brackets) {
            switch (ch) {
                case ')':
                    if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    chekBracket = stack.getLast();
                    if (chekBracket == '(') {
                        stack.removeLast();
                    } else {
                        out.println("no");
                        return;
                    }
                    break;
                case ']':
                    if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    chekBracket = stack.getLast();
                    if (chekBracket == '[') {
                        stack.removeLast();
                    } else {
                        out.println("no");
                        return;
                    }
                    break;
                case '}':
                    if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    chekBracket = stack.getLast();
                    if (chekBracket == '{') {
                        stack.removeLast();
                    } else {
                        out.println("no");
                        return;
                    }
                    break;
                default:
                    stack.add(ch);
                    break;
            }
        }
        if (stack.size() == 0) {
            out.println("yes");
            return;
        }
        out.println("no");
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
