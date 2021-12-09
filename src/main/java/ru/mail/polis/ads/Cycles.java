package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

//https://www.eolymp.com/ru/submissions/10085735
public class Cycles {
    private static final byte WHITE = 0;
    private static final byte GREY = 1;
    private static final byte BLACK = 2;

    private static byte[] colors;
    private static boolean[] isInCycle;
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static Set<Integer>[] adjacencies;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        colors = new byte[v + 1];
        adjacencies = new Set[v + 1];
        isInCycle = new boolean[v + 1];
        for (int i = 1; i <= v; i++) {
            adjacencies[i] = new HashSet<>();
        }
        for (int i = 1; i <= e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            adjacencies[from].add(to);
            adjacencies[to].add(from);
        }

        for (int i = 1; i <= v; i++) {
            if (colors[i] == WHITE) {
                dfs(i, -1);
                stack.clear();
            }
        }
        for (int i = 1; i <= v; i++) {
            if (isInCycle[i]) {
                out.println("Yes");
                out.println(i);
                return;
            }
        }
        out.println("No");
    }

    private static void dfs(int v, int cameFrom) {
        colors[v] = GREY;
        stack.push(v);
        for (Integer to : adjacencies[v]) {
            if (to == cameFrom) {
                continue;
            }
            if (colors[to] == WHITE) {
                dfs(to, v);
            }
            if (colors[to] == GREY) {
                isInCycle[to] = true;
                for (Integer vertex : stack) {
                    if (vertex.equals(to) || isInCycle[vertex]) {
                        break;
                    } else {
                        isInCycle[vertex] = true;
                    }
                }
            }
        }
        stack.remove();
        colors[v] = BLACK;
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