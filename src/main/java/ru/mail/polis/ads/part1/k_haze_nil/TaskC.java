package ru.mail.polis.ads.part1.k_haze_nil;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */


public final class TaskC {


    private TaskC() {
        // Should not be instantiated
    }


    static class Queue {
        private static final LinkedList<Integer> container = new LinkedList<>();
        private static final String ERROR = "error";
        private static final String OK = "ok";

        public String push(int n) {
            container.push(n);
            return OK;
        }

        public String pop() {
            if (container.isEmpty()) {
                return ERROR;
            } else {
                return String.valueOf(container.pop());
            }
        }

        public String back() {
            if (container.isEmpty()) {
                return ERROR;
            } else {
                return String.valueOf(container.get(0));
            }
        }

        public Integer size() {
            return container.size();
        }

        public String clear() {
            container.clear();
            return OK;
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        boolean processing = true;
        String command;

        while (processing) {
            command = in.next();
            switch (command) {
                case ("push"):
                    out.println(queue.push(in.nextInt()));
                    break;
                case ("pop"):
                    out.println(queue.pop());
                    break;
                case ("back"):
                    out.println(queue.back());
                    break;
                case ("size"):
                    out.println(queue.size());
                    break;
                case ("clear"):
                    out.println(queue.clear());
                    break;
                case ("exit"):
                    out.println("bye");
                    processing = false;
                    break;
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
}
