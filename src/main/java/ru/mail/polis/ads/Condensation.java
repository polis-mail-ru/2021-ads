package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

//https://www.eolymp.com/ru/submissions/10080727
public class Condensation {

    private static Set<Integer>[] adjacencies;
    private static Set<Integer>[] transposed;
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static boolean[] visited;
    private static int currentComp;
    private static int[] components;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        adjacencies = new Set[v + 1];
        transposed = new Set[v + 1];
        visited = new boolean[v + 1];
        components = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            adjacencies[i] = new HashSet<>();
            transposed[i] = new HashSet<>();
        }
        for (int i = 1; i <= e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            adjacencies[from].add(to);
            transposed[to].add(from);
        }

        topologicalSort();
        Map<Integer, Set<Integer>> connections = new HashMap<>();
        currentComp = 1;
        visited = new boolean[v + 1];
        for (Integer vertex : stack) {
            if (!visited[vertex]) {
                dfsCondensation(vertex);
                currentComp++;
            }
        }

        for (int i = 1; i <= v; i++) {
            for (Integer to : adjacencies[i]) {
                if (components[to] != components[i]) {
                    connections.computeIfAbsent(components[to], k -> new HashSet<>()).add(components[i]);
                }
            }
        }
        out.print(connections.values().stream().mapToInt(Set::size).sum());
    }

    private static void topologicalSort() {
        for (int i = 1; i <= adjacencies.length - 1; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private static void dfs(int v) {
        visited[v] = true;
        for (Integer to : adjacencies[v]) {
            if (!visited[to]) {
                dfs(to);
            }
        }
        stack.push(v);
    }

    private static void dfsCondensation(int v) {
        visited[v] = true;
        components[v] = currentComp;
        for (Integer to : transposed[v]) {
            if (!visited[to]) {
                dfsCondensation(to);
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
