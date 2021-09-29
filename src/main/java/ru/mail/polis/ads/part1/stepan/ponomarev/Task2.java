package ru.mail.polis.ads.part1.stepan.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task2 {
    private static final String EMPTY_QUEUE_ERROR_MSG = "Queue is empty";
    private static final String UNSUPPORTED_COMMAND_ERROR_MSG = "Unsupported command:";
    
    private static final String EXIT_TEXT = "bye";
    private static final String OK_TEXT = "ok";
    private static final String ERROR_TEXT = "error";
    
    
    private static final Queue queue = new Queue();

    private static void solve(final FastScanner in, final PrintWriter out) {
        Command command;
        do {
            command = Command.parse(in.next());

            Integer argument = command.isHasArgument() ? Integer.parseInt(in.next()) : null;
            String result = handleCommand(command, argument);
            out.println(result);
        } while (command != Command.EXIT);
    }

    private static String handleCommand(Command command, Integer arg) {
        try {
            switch (command) {
                case PUSH:
                    queue.push(arg);
                    return OK_TEXT;
                case POP:
                    return String.valueOf(queue.pop());
                case FRONT:
                    return String.valueOf(queue.front());
                case SIZE:
                    return String.valueOf(queue.size());
                case CLEAN:
                    queue.clear();
                    return OK_TEXT;
                case EXIT:
                    return EXIT_TEXT;
                default:
                    throw new IllegalArgumentException(UNSUPPORTED_COMMAND_ERROR_MSG + " " + command.getCommand());
            }
        } catch (IllegalStateException e) {
            return ERROR_TEXT;
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private enum Command {
        PUSH("push", true),
        POP("pop"),
        FRONT("front"),
        SIZE("size"),
        CLEAN("clear"),
        EXIT("exit");

        private final String command;
        private final boolean hasArgument;

        Command(String command) {
            this(command, false);
        }

        Command(String command, boolean hasArgument) {
            this.command = command;
            this.hasArgument = hasArgument;
        }

        public static Command parse(String command) {
            for (Command c : Command.values()) {
                if (c.command.equals(command)) {
                    return c;
                }
            }

            throw new IllegalArgumentException(UNSUPPORTED_COMMAND_ERROR_MSG + " " + command);
        }

        public boolean isHasArgument() {
            return hasArgument;
        }

        public String getCommand() {
            return command;
        }
    }

    private static class Queue {
        private Node first;
        private Node last;
        private int size;

        public Queue() {
            this.first = null;
            this.last = null;
            this.size = 0;
        }

        public void push(final int n) {
            if (size == 0) {
                this.first = new Node(n, null);
                this.last = this.first;
            } else {
                this.last.nextNode = new Node(n, null);
                this.last = this.last.nextNode;
            }

            this.size++;
        }

        public int pop() {
            if (size == 0) {
                throw new IllegalStateException(EMPTY_QUEUE_ERROR_MSG);
            }

            int value = this.front();
            this.first = this.first.nextNode;
            this.size--;

            return value;
        }

        public int front() {
            if (size == 0) {
                throw new IllegalStateException(EMPTY_QUEUE_ERROR_MSG);
            }

            return this.first.data;
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.first = null;
            this.last = null;
            this.size = 0;
        }

        private static class Node {
            private final int data;
            private Node nextNode;

            public Node(int data, Node nextNode) {
                this.data = data;
                this.nextNode = nextNode;
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
}
