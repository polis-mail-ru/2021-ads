package ru.mail.polis.ads.part1.stepan.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task3 {
    private static final String EMPTY_QUEUE_ERROR_MSG = "Queue is empty";
    private static final String UNSUPPORTED_COMMAND_ERROR_MSG = "Unsupported command:";
    
    private static final String EXIT_TEXT = "bye";
    private static final String OK_TEXT = "ok";
    private static final String ERROR_TEXT = "error";
    
    
    private static final Stack STACK = new Stack();

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
                    STACK.push(arg);
                    return OK_TEXT;
                case POP:
                    return String.valueOf(STACK.pop());
                case BACK:
                    return String.valueOf(STACK.back());
                case SIZE:
                    return String.valueOf(STACK.size());
                case CLEAN:
                    STACK.clear();
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
        BACK("back"),
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

    private static class Stack {
        private Node head;
        private int size;

        public Stack() {
            this.head = null;
            this.size = 0;
        }

        public void push(final int n) {
            this.head = new Node(n, this.head);
            this.size++;
        }

        public int pop() {
            int value = this.back();
            this.head = this.head.nextNode;
            this.size--;

            return value;
        }

        public int back() {
            if (size == 0) {
                throw new IllegalStateException(EMPTY_QUEUE_ERROR_MSG);
            }

            return this.head.data;
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.head = null;
            this.size = 0;
        }

        private static class Node {
            private final int data;
            private final Node nextNode;

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
