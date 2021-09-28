package ru.mail.polis.ads.part1.FetisovVladislav;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Task2 {
    private static class MyQueue<T> {
        private final LinkedList<T> queue;

        public MyQueue() {
            this.queue = new LinkedList<>();
        }

        public void push(T el) {
            queue.addLast(el);
        }

        public T pop() {
            return (!queue.isEmpty()) ? queue.removeFirst() : null;
        }

        public T front() {
            return (!queue.isEmpty()) ? queue.getFirst() : null;
        }

        public int size() {
            return queue.size();
        }

        public void clear() {
            queue.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue<Integer> queue = new MyQueue<>();
        String comm = in.next();
        while (!comm.equals("exit")) {
            switch (comm) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    Integer result = queue.pop();
                    out.println(result == null ? "error" : result.toString());
                    break;
                case "front":
                    result = queue.front();
                    out.println(result == null ? "error" : result.toString());
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
            }
            comm = in.next();
        }
        out.print("bye");
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

