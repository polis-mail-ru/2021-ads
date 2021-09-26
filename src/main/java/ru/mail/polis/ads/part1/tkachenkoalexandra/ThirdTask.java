package ru.mail.polis.ads.part1.tkachenkoalexandra;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class ThirdTask {
    private ThirdTask() {
    }

    private static class Stack<T> {
        private ArrayList<T> arrayList;

        public Stack() {
            arrayList = new ArrayList<T>();
        }

        public void push(T object) {
            arrayList.add((T) object);
        }

        public T pop() {
            return arrayList.remove(arrayList.size() - 1);
        }

        public T back() {
            return arrayList.get(arrayList.size() - 1);
        }

        public int size() {
            return arrayList.size();
        }

        public void clear() {
            arrayList.clear();
        }
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack<Integer>();
        String string = in.next();
        while (!string.equals("exit")) {
            switch (string) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                case "back":
                    out.println(stack.size() == 0 ? "error"
                            : (string.equals("pop") ? stack.pop() : stack.back()));
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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

