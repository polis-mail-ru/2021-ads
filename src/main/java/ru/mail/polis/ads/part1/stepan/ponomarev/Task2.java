package ru.mail.polis.ads.part1.stepan.ponomarev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task2 {
    public static final String EXIT_TEXT = "bye";
    public static final String OK_TEXT = "ok";
    public static final Queue queue = new Queue();

    private static void solve(final FastScanner in, final PrintWriter out) {
        Command command;
        do {
            command = Command.parse(in.next());
            handleCommand(command, command.isHasArgument() ? Integer.parseInt(in.next()) : null, in, out);
        } while (command != Command.EXIT);
    }

    private static void handleCommand(Command command, Integer arg, final FastScanner in, final PrintWriter out) {
        switch (command) {
            case PUSH:
                queue.push(arg);
                out.println(OK_TEXT);
                break;
            case POP:
                out.println(queue.pop());
                break;
            case FRONT:
                out.println(queue.front());
                break;
            case SIZE:
                out.println(queue.size());
                break;
            case CLEAN:
                queue.clear();
                out.println(OK_TEXT);
                break;
            case EXIT:
                out.println(EXIT_TEXT);
                break;
            default:
                throw new IllegalArgumentException("Unsupported command: " + command.getCommand());
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

enum Command {
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

    public boolean isHasArgument() {
        return hasArgument;
    }

    public String getCommand() {
        return command;
    }

    public static Command parse(String command) {
        for (Command c : Command.values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Unsupported command: " + command);
    }
}

class Queue {
    private Node head = null;
    private int size = 0;

    public void push(int value) {
        head = new Node(head, value);
        size++;
    }

    public int pop() {
        int value = head.value;
        head = head.prevNode;
        size--;

        return value;
    }

    public int front() {
        if (head.prevNode == null) {
            int value = head.value;
            head = null;
            size--;

            return value;
        }

        Node currNode = head;
        while (currNode.prevNode.prevNode != null) {
            currNode = currNode.prevNode;
        }

        int value = currNode.value;
        currNode.prevNode = null;
        size--;

        return value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    private final class Node {
        private Node prevNode;
        private final int value;

        public Node(Node prevNode, int value) {
            this.prevNode = prevNode;
            this.value = value;
        }
    }
}
