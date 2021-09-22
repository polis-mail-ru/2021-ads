package ru.mail.polis.ads.part1.sorgeligt;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack<Integer> stack = new MyStack<>();

        boolean flagExit = true;
        while (flagExit) {
            String operation = in.next();
            switch (operation) {
                case "push":
                    int value = in.nextInt();
                    stack.push(value);
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (NoSuchElementException e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.back());
                    } catch (NoSuchElementException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    flagExit = false;
                    out.println("bye");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation");
            }
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
