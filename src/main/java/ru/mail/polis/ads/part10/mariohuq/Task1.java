package ru.mail.polis.ads.part10.mariohuq;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://www.eolymp.com/ru/problems/3835">3835. Минимальный каркас</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10118438">E-olymp submission</a>
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(mEdges, nVertices);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        g.sortEdges();
        out.println(kruskalMstWeight(g));
    }

    private static int kruskalMstWeight(Graph g) {
        final int[] f = IntStream.range(0, g.vertices()).toArray();
        int result = 0;
        int components = f.length;
        for (Edge edge : g.edges()) {
            if (f[edge.from] == f[edge.to]) {
                continue;
            }
            result += edge.weight;
            replace(f, f[edge.from], f[edge.to]);
            components--;
            if (components <= 1) {
                break;
            }
        }
        return result;
    }

    private static void replace(int[] f, int old, int new1) {
        for (int i = 0; i < f.length; i++) {
            if (f[i] == old) {
                f[i] = new1;
            }
        }
    }

    private static class Graph {
        private final Edge[] edges;
        private int edgesCount;
        private final int verticesCount;

        public Graph(int edgesCount, int verticesCount) {
            edges = new Edge[edgesCount];
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
