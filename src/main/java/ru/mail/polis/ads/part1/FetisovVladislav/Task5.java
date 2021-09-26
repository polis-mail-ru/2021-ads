package ru.mail.polis.ads.part1.FetisovVladislav;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task5 {
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
            return !stack.isEmpty() ? stack.get(stack.size() - 1) : null;
        }

        public int size() {
            return stack.size();
        }

        public void clear() {
            stack.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack<Integer> stack = new MyStack<>();
        String numOrOper = in.next();
        while (numOrOper != null) {
            switch (numOrOper) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(numOrOper));
                    break;
            }
            numOrOper = in.next();
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
            while (tokenizer == null) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return (tokenizer.hasMoreTokens()) ? tokenizer.nextToken() : null;
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


