package ru.mail.polis.ads.part1.sorgeligt;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task5 {

    private Task5() {
        // Should not be instantiated
    }

    private static int operationSolve(char operation, int b, int a) {
        switch (operation) {
            case '+':
                return a + b;
            case '*':
                return a * b;
            case '-':
                return a - b;
            default:
                return 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack<Integer> stack = new MyStack<>();
        String str = in.nextLine();
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ' ')
                continue;
            if (charAt >= '0' && charAt <= '9') {
                stack.push(Integer.parseInt(String.valueOf(charAt)));
            } else {
                stack.push(operationSolve(charAt, stack.pop(), stack.pop()));
            }
        }
        out.println(stack.back());

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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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


