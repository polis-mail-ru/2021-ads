package ru.mail.polis.ads.part10.mariohuq;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.eolymp.com/ru/problems/1453">1453. Форд-Беллман</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10119975">E-olymp submission</a>
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    public static final int INFINITY = 30_000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(nVertices, mEdges);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        int[] answer = fordBellman(g);
        for (int distance : answer) {
            out.printf("%d ", distance);
        }
        out.println();
    }

    /**
     * Расстояния от вершины 0 до всех.
     */
    private static int[] fordBellman(Graph g) {
        final int[] distance = new int[g.vertices()];
        Arrays.fill(distance, INFINITY);
        distance[0] = 0;
        int n = g.vertices() - 1;
        for (int i = 0; i < n; i++) {
            for (Edge edge : g.edges()) {
                if (distance[edge.from] < INFINITY && distance[edge.to] > distance[edge.from] + edge.weight) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }
        return distance;
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

    private static class Graph {
        private final List<Edge> edges;
        private final int nVertices;

        public Graph(int nVertices, int mEdges) {
            this.nVertices = nVertices;
            edges = new ArrayList<>(mEdges);
        }

        public int vertices() {
            return nVertices;
        }

        public Iterable<? extends Edge> edges() {
            return edges;
        }

        public void addEdge(int from, int to, int weight) {
            edges.add(new Edge(from, to, weight));
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
