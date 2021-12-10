package ru.mail.polis.ads.part9.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static ArrayList<Integer>[] graph;
    private static final Stack<Integer> stack = new Stack<>();
    private static boolean[] visited;
    private static boolean[] isBlack;
    private static boolean hasCycle;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n];
        visited = new boolean[n];
        isBlack = new boolean[n];
        for (int i = 0; i < m; i++) {
            int k = in.nextInt() - 1;
            if (graph[k] == null) {
                graph[k] = new ArrayList<>();
            }
            graph[k].add(in.nextInt() - 1);
        }
        topologicalSort();
        if (hasCycle) {
            out.print(-1);
            return;
        }
        while (!stack.isEmpty()) {
            out.print((stack.pop() + 1) + " ");
        }
    }

    private static void dfs(int u) {
        visited[u] = true;
        if (graph[u] != null) {
            for (int el : graph[u]) {
                if (!visited[el]) {
                    dfs(el);
                } else if (!isBlack[el]) {
                    hasCycle = true;
                }
            }
        }
        stack.push(u);
        isBlack[u] = true;
    }

    private static void topologicalSort() {
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i] && !hasCycle) {
                dfs(i);
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
