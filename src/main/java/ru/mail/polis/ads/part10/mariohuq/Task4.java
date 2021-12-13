package ru.mail.polis.ads.part10.mariohuq;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * <a href="https://www.eolymp.com/ru/problems/325">325. Опасный маршрут</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10120299">E-olymp submission</a>
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(nVertices, mEdges);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        g.sortEdges();
        out.println(kruskalMstMinMaxPath(g));
    }

    private static int kruskalMstMinMaxPath(Graph g) {
        DisjointSetUnion f = new DisjointSetUnion(g.vertices());
        for (Edge edge : g.edges()) {
            if (f.get(edge.from) == f.get(edge.to)) {
                continue;
            }
            f.union(edge.to, edge.from);
            if (f.get(0) == f.get(g.vertices() - 1)) {
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static class DisjointSetUnion {
        final int[] parent;
        final int[] rank;

        public DisjointSetUnion(int size) {
            parent = IntStream.range(0, size).toArray();
            rank = new int[size];
        }

        public int get(int x) {
            if (x != parent[x]) {
                parent[x] = get(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            x = get(x);
            y = get(y);
            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
            if (rank[x] == rank[y]) {
                rank[y]++;
            }
        }
    }

    private static class Graph {
        private final Edge[] edges;
        private int edgesCount;
        private final int verticesCount;

        public Graph(int verticesCount, int edgesCount) {
            this(verticesCount, new Edge[edgesCount]);
        }

        private Graph(int verticesCount, Edge[] edges) {
            this.edges = edges;
            this.verticesCount = verticesCount;
        }

        public void addEdge(int from, int to, int weight) {
            edges[edgesCount++] = new Edge(from, to, weight);
        }

        public Edge[] edges() {
            return edges;
        }

        public void sortEdges() {
            Arrays.sort(edges, Comparator.comparing(e -> e.weight));
        }

        public int vertices() {
            return verticesCount;
        }

        public int edgesCount() {
            return edgesCount;
        }

        public Graph withoutEdgesLess(int weight) {
            return new Graph(verticesCount, Arrays.stream(edges).filter(e -> e.weight >= weight).toArray(Edge[]::new));
        }
    }

    private static class Edge {
        public final int from;
        public final int to;
        public final int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
