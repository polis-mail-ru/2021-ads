package ru.mail.polis.ads.part1.sorgeligt;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue<Integer> queue = new MyQueue<>();

        boolean flagExit = true;
        while (flagExit) {
            String operation = in.next();
            switch (operation) {
                case "push":
                    int value = in.nextInt();
                    queue.push(value);
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(queue.pop());
                    } catch (NoSuchElementException e) {
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(queue.front());
                    } catch (NoSuchElementException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
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

    static class MyQueue<T> {
        private Node<T> first;
        private Node<T> last;
        private int size = 0;

        static class Node<T> {
            T field;
            Node<T> next;

            public Node(T field) {
                this.field = field;
            }
        }

        void push(T value) {
            if (first == null) {
                first = new Node<>(value);
                last = first;
            } else {
                last.next = new Node<>(value);
                last = last.next;
            }
            size++;
        }

        T pop() {
            if (first != null) {
                T fieldValue = first.field;
                first = first.next;
                size--;
                return fieldValue;
            } else {
                throw new NoSuchElementException("Queue empty");
            }
        }

        T front() {
            if (first != null) {
                return first.field;
            } else {
                throw new NoSuchElementException("Queue empty");
            }
        }

        int size() {
            return size;
        }

        void clear() {
            first = null;
            last = null;
            size = 0;
        }
    }
}
