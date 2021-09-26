package ru.mail.polis.ads.part1.tkachenkoalexandra;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class FifthTask {
    private FifthTask() {
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
        Stack<Integer> stack = new Stack<>();
        String next;
        while (in.hasNext()) {
            next = in.next();
            switch (next) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first - second);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(next));
            }
        }
        out.println(stack.pop());
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

        boolean hasNext() {
            return (tokenizer == null) || (tokenizer.hasMoreTokens());
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