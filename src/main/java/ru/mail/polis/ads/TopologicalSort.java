package ru.mail.polis.ads;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class TopologicalSort {
    static Map<Integer, List<Integer>> edges;
    static boolean[] visited;
    static char[] color;
    static List<Integer> stack;
    static int n;

    //https://www.eolymp.com/ru/submissions/9978007
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        visited = new boolean[n + 1];
        color = new char[n + 1];
        stack = new LinkedList<>();
        edges = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            visited[i] = false;
            color[i] = 'w';
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            edges.compute(a, (k, v) -> {
                if (v == null) {
                    v = new LinkedList<>();
                }
                v.add(in.nextInt());
                return v;
            });
        }
        topologicalSort();
        Collections.reverse(stack);
        if (stack.size() != n) {
            System.out.println("-1");
            return;
        }
        stack.forEach(a -> out.print(a + " "));
    }

    private static void topologicalSort() {
        for (int i = 1; i < n + 1; i++) {
            if (checkForCycle(i)) {
                stack.clear();
                return;
            }
            if (!visited[i])
                dfs(i);
        }
    }

    static boolean checkForCycle(int v) {
        color[v] = 'g';
        for (Integer to : edges.getOrDefault(v, Collections.emptyList())) {
            if (color[to] == 'w') {
                if (checkForCycle(to)) return true;
            } else if (color[to] == 'g') {
                return true;
            }
        }
        color[v] = 'b';
        return false;
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

    static class Edge {
        int a, b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
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

