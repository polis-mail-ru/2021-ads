package ru.mail.polis.ads.ponomarev.stepan.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {
    private static class Derevo {
        private Node head = null;
        public int size = 0;

        private static class Node {
            int v;
            Node left;
            Node right;

            public Node(int v, Node left, Node right) {
                this.v = v;
                this.left = left;
                this.right = right;
            }

            public Node(int v) {
                this(v, null, null);
            }
        }

        public void add(int v) {
            if (head == null) {
                head = new Node(v);
                size++;
                return;
            }

            Node curr = head;
            while (true) {
                if (curr.v == v) {
                    return;
                }

                if (curr.v < v) {
                    if (curr.left == null) {
                        curr.left = new Node(v, null, null);
                        size++;
                        return;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    if (curr.right == null) {
                        curr.right = new Node(v, null, null);
                        size++;
                        return;
                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
    }

    private static final Derevo derevo = new Derevo();

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();

        for (int i = 0; i < amount; i++) {
            derevo.add(in.nextInt());
        }

        int sizeBefore = derevo.size;

        amount = in.nextInt();
        for (int i = 0; i < amount; i++) {
            derevo.add(in.nextInt());
        }

        out.println(sizeBefore == derevo.size ? "YES" : "NO");
    }

    private static int getIndex(int minValue, int currentValue) {
        return currentValue - minValue;
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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