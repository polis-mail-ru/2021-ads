package ru.mail.polis.ads.part1.tkachenkoalexandra;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public final class FourthTask {
    private FourthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        String string = in.next();
        Deque<Character> deque = new LinkedList<>();
        if (Pattern.compile("[\\(*\\{*\\[*\\)*\\}*\\]*\\s*]*").matcher(string).matches()) {
            char[] charArray = string.replaceAll("\\s+", "").toCharArray();
            for (char ch : charArray) {
                if ((ch == '{') || (ch == '[') || (ch == '(')) {
                    deque.addFirst(ch);
                } else {
                    if (((ch == ']') && !deque.isEmpty() && (deque.peekFirst() == '['))
                            || ((ch == '}') && !deque.isEmpty() && (deque.peekFirst() == '{'))
                            || ((ch == ')') && !deque.isEmpty() && (deque.peekFirst() == '('))) {
                        deque.removeFirst();
                    } else {
                        out.println("no");
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("The expression contains something other than whitespace" +
                    " characters and valid types of parentheses.\n");
        }
        if (deque.size() != 0) {
            out.println("no");
            return;
        }
        out.println("yes");
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
