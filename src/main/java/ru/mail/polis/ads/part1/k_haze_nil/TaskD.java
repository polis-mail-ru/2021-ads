package ru.mail.polis.ads.part1.k_haze_nil;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */


public final class TaskD {


    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Character> stack = new LinkedList<>();
        String expr = in.next();

        for (int i = 0; i < expr.length(); i++) {
            char currChar = expr.charAt(i);

            if (currChar == '(' || currChar == '[' || currChar == '{') {
                stack.push(currChar);
                continue;
            }

            if (stack.isEmpty()
                    || (currChar == ')' && stack.pop() != '(')
                    || (currChar == ']' && stack.pop() != '[')
                    || (currChar == '}' && stack.pop() != '{')) {
                out.print("no");
                return;
            }
        }
        if (stack.isEmpty()) {
            out.print("yes");
        } else {
            out.print("no");
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
