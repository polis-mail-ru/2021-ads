package ru.mail.polis.ads;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem solution template.
 */
public class Condensation {
    static Map<Integer, List<Integer>> edges, transgraph;

    static boolean[] visited;
    static List<Integer> stack;
    static Map<Integer, Set<Integer>> connections;
    static int n, curComp;
    static int[] components;

    //https://www.eolymp.com/ru/submissions/10042967
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        connections = new HashMap<>();
        visited = new boolean[n + 1];
        components = new int[n + 1];
        stack = new LinkedList<>();
        transgraph = new HashMap<>();
        edges = new HashMap<>();
        Arrays.fill(visited, false);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            edges.compute(a, (k, v) -> {
                if (v == null) {
                    v = new LinkedList<>();
                }
                v.add(b);
                return v;
            });
            transgraph.compute(b, (k, v) -> {
                if (v == null) {
                    v = new LinkedList<>();
                }
                v.add(a);
                return v;
            });
        }
        topologicalSort();
        Collections.reverse(stack);


        curComp = 1;
        Arrays.fill(visited, false);
        for (Integer v : stack) {
            if (!visited[v]) {
                dfsCondensation(v);
                curComp++;
            }
        }

        for (Map.Entry<Integer, List<Integer>> i : edges.entrySet()) {
            for (Integer v : i.getValue()) {
                if (components[v] != components[i.getKey()]) {
                    connections.compute(components[v], (key, value) -> {
                        if (value == null) {
                            value = new HashSet<>();
                        }
                        value.add(components[i.getKey()]);
                        return value;
                    });
                }
            }
        }
        AtomicInteger count = new AtomicInteger();
        connections.forEach((k, v) -> count.addAndGet(v.size()));
        out.print(count.get());
    }

    private static void topologicalSort() {
        for (Integer i : edges.keySet()) {
            if (!visited[i])
                dfs(i);
        }
    }

    static void dfsCondensation(int v) {
        visited[v] = true;
        components[v] = curComp;
        for (Integer to : transgraph.getOrDefault(v, Collections.emptyList())) {
            if (!visited[to])
                dfsCondensation(to);
        }
    }

    private static void dfs(int b) {
        visited[b] = true;
        for (Integer to : edges.getOrDefault(b, Collections.emptyList())) {
            if (!visited[to])
                dfs(to);
        }
        stack.add(b);
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

