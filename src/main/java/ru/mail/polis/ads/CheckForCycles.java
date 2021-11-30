package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class CheckForCycles {
    static Map<Integer, Set<Integer>> edges;
    static char[] color;
    static Deque<Integer> stack;
    static int n;

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        color = new char[n + 1];
        edges = new HashMap<>();
        stack = new LinkedList<>();
        for (int i = 0; i < n + 1; i++) {
            color[i] = 'w';
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int to = in.nextInt();
            edges.compute(a, (k, v) -> {
                if (v == null) {
                    v = new HashSet<>();
                }
                v.add(to);
                return v;
            });
            edges.compute(to, (k, v) -> {
                if (v == null) {
                    v = new HashSet<>();
                }
                v.add(a);
                return v;
            });
        }
        int min = n + 2;
        for (int i = 1; i < n + 1; i++) {
            int a = hasCycle(i);
            if (a != -1 && a < min) min = a;
            stack.clear();
        }
        if (min != n + 2) {
            out.println("Yes");
            out.print(min);
            return;
        }
        out.print("No");
    }

    static int hasCycle(int v) {
        Integer prev = stack.peekFirst();
        stack.addFirst(v);
        for (Integer to : edges.getOrDefault(v, Collections.emptySet())) {
            if (to.equals(prev)) {
                continue;
            }
            if (stack.contains(to)) {
                int min = to;
                int cur;
                while ((cur = stack.poll()) != to) {
                    if (cur < min) {
                        min = cur;
                    }
                }
                return min;
            } else {
                return hasCycle(to);
            }
        }
        stack.poll();
        return -1;
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
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
