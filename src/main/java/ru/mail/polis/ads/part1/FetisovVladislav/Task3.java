package ru.mail.polis.ads.part1.FetisovVladislav;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Task3 {

    static class MyStack<T> {
        private final LinkedList<T> stack;

        public MyStack() {
            this.stack = new LinkedList<>();
        }

        public void push(T el) {
            stack.addFirst(el);
        }

        public T pop() {
            return !stack.isEmpty() ? stack.removeFirst() : null;
        }

        public T back() {
            return !stack.isEmpty() ? stack.getFirst() : null;
        }

        public int size() {
            return stack.size();
        }

        public void clear() {
            stack.clear();
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack<Integer> stack = new MyStack<>();
        String comm = in.next();
        while (!comm.equals("exit")) {
            switch (comm) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    Integer result = stack.pop();
                    out.println(result == null ? "error" : result.toString());
                    break;
                case "back":
                    result = stack.back();
                    out.println(result == null ? "error" : result.toString());
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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
