package ru.mail.polis.ads.part1.sorgeligt;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task4 {

    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack<Character> stack = new MyStack<>();
        String parentheses = in.next();

        String answer = "unknown";
        for (int i = 0; i < parentheses.length(); i++) {
            char bracket = parentheses.charAt(i);
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                stack.push(bracket);
            } else {
                boolean flag;
                try {
                    flag = (bracket - stack.back()) > 0 && (bracket - stack.back()) <= 2;
                } catch (NoSuchElementException e) {
                    answer = "no";
                    break;
                }
                if (flag) {
                    stack.pop();
                } else {
                    answer = "no";
                    break;
                }
            }
        }
        if (answer.equals("unknown")) {
            out.println(stack.size() == 0 ? "yes" : "no");
        } else {
            out.println(answer);
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

    static class MyStack<T> {
        private Node<T> top;
        private int size = 0;

        static class Node<T> {
            T field;
            Node<T> prev;

            public Node(T field) {
                this.field = field;
            }
        }

        void push(T value) {
            if (top != null) {
                Node<T> newNode = new Node<>(value);
                newNode.prev = top;
                top = newNode;
            } else {
                top = new Node<>(value);
            }
            size++;
        }

        T pop() {
            if (top != null) {
                T fieldValue = top.field;
                top = top.prev;
                size--;
                return fieldValue;
            } else {
                throw new NoSuchElementException("Queue empty");
            }
        }

        T back() {
            if (top != null) {
                return top.field;
            } else {
                throw new NoSuchElementException("Queue empty");
            }
        }

        int size() {
            return size;
        }

        void clear() {
            top = null;
            size = 0;
        }
    }
}


