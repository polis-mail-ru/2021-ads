package ru.mail.polis.ads.part10.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Bridges {

    private static int[] enterTime;
    private static int[] fup;
    private static boolean[] used;
    private static int timer;
    private static List<Integer>[] graph;
    private static final Set<Integer> bridges = new HashSet<>();
    private static final Map<Edge, Integer> edges = new HashMap<>();

    private Bridges() {
        // Should not be instantiated
    }

    private static class Edge {
        private final int from;
        private final int to;

        public Edge(int from, int to) {
            if (from < to) {
                this.from = from;
                this.to = to;
                return;
            }
            this.from = to;
            this.to = from;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (from != edge.from) return false;
            return to == edge.to;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int from;
        int to;
        used = new boolean[n];
        fup = new int[n];
        enterTime = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            graph[from].add(to);
            graph[to].add(from);
            edges.put(new Edge(from, to), i);
            edges.put(new Edge(to, from), i);
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i, -1);
            }
        }

        out.println(bridges.size());
        for (int i = 0; i < m; i++) {
            if (bridges.contains(i)) {
                out.printf("%d ", i + 1);
            }
        }
    }

    private static void dfs(int vertex, int parent) {
        used[vertex] = true;
        enterTime[vertex] = timer;
        fup[vertex] = timer++;
        for (int to : graph[vertex]) {
            if (to != parent) {
                if (used[to]) {
                    fup[vertex] = Math.min(fup[vertex], enterTime[to]);
                } else {
                    dfs(to, vertex);
                    fup[vertex] = Math.min(fup[vertex], fup[to]);
                    if (fup[to] > enterTime[vertex]) {
                        bridges.add(edges.get(new Edge(vertex, to)));
                    }
                }
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