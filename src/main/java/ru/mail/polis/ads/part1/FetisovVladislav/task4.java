package ru.mail.polis.ads.part1.FetisovVladislav;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class task4 {
    static class MyStack<T> {
        private final LinkedList<T> stack;

        public MyStack() {
            this.stack = new LinkedList<>();
        }

        public void push(T el) {
            stack.addFirst(el);
        }

        public T pop() {
            return !stack.isEmpty() ? stack.removeFirst() : null;
        }

        public T back() {
            return !stack.isEmpty() ? stack.getFirst() : null;
        }

        public int size() {
            return stack.size();
        }

        public void clear() {
            stack.clear();
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {

        HashSet<Character> brackets = Arrays.stream(new Character[]{'{', '}', '[', ']', '(', ')'})
                .collect(Collectors.toCollection(HashSet::new));
        MyStack<Character> stack = new MyStack<>();
        for (char bracketCandidate : in.next().toCharArray()) {
            if (!brackets.contains(bracketCandidate)) {
                out.println("no");
                return;
            }
            if (bracketCandidate == '(' || bracketCandidate == '{' || bracketCandidate == '[') {
                stack.push(bracketCandidate);
            } else {
                Character result = stack.pop();
                if (result == null || (result == '[' && bracketCandidate != ']')
                        || (result == '{' && bracketCandidate != '}')
                        || result == '(' && bracketCandidate != ')') {
                    out.println("no");
                    return;
                }
            }
        }
        out.println(stack.size() == 0 ? "yes" : "no");
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