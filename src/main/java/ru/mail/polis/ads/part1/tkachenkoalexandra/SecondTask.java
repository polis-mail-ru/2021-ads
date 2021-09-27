package ru.mail.polis.ads.part1.tkachenkoalexandra;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class SecondTask {
    private SecondTask() {
    }
    private static class Queue<T> {
        private LinkedList<T> list;

        public Queue() {
            list = new LinkedList<T>();
        }

        public void push(T object) {
            list.add((T) object);
        }

        public T pop() {
            return list.removeFirst();
        }

        public T front() {
            return list.getFirst();
        }

        public int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
        }
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        Queue<Integer> queue = new Queue<>();
        String string = in.next();
        while (!string.equals("exit")) {
            switch (string) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                case "front":
                    out.println(queue.size() == 0 ? "error"
                            : (string.equals("pop") ? queue.pop() : queue.front()));
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                default:
                    throw new IllegalArgumentException("An unknown command has been submitted to the input.\n");
            }
            string = in.next();
        }
        out.println("bye");
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
