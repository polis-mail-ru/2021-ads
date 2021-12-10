package ru.mail.polis.ads.part9.nikita17th;

import java.io.*;
import java.util.*;


public class GraphCondensation {
    private static int[] color;
    private static boolean[] used;
    private static final List<Integer> order = new ArrayList<>();
    private static List<Integer>[] graph;
    private static List<Integer>[] transposeGraph;

    private GraphCondensation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a;
        int b;
        color = new int[n];
        graph = new ArrayList[n];
        used = new boolean[n];
        transposeGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            transposeGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
            transposeGraph[b].add(a);
        }

        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                topologicalSort(i);
            }
        }
        for (int i = 0; i < n; i++) {
            used[i] = false;
        }
        int countColors = 0;
        for (int i = n - 1; i >= 0; i--) {
            int v = order.get(i);
            if (!used[v]) {
                dfs(v, countColors);
                countColors++;
            }
        }

        Set<Integer>[] components = new HashSet[countColors];
        for (int i = 0; i < countColors; i++) {
            components[i] = new HashSet<>();
        }
        for (int vertex = 0; vertex < n; vertex++) {
            for (int to : graph[vertex]) {
                if (color[vertex] != color[to]) {
                    components[color[vertex]].add(color[to]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < countColors; i++) {
            ans += components[i].size();
        }
        out.println(ans);
    }

    private static void topologicalSort(int vertex) {
        used[vertex] = true;
        for (int to : graph[vertex]) {
            if (!used[to]) {
                topologicalSort(to);
            }
        }
        order.add(vertex);
    }

    private static void dfs(int vertex, int countColors) {
        used[vertex] = true;
        color[vertex] = countColors;
        for (int to : transposeGraph[vertex]) {
            if (!used[to]) {
                dfs(to, countColors);
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
