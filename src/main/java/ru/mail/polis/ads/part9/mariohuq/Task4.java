package ru.mail.polis.ads.part9.mariohuq;

import java.io.*;
import java.util.*;

/**
 * <a href="https://www.e-olymp.com/ru/problems/1947">1947. Конденсация графа</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10062314">E-olymp submission</a>
 */
public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(nVertices);
        for (int i = 0; i < mEdges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g.addArrow(from, to);
        }
        out.println(new EdgesInCondensedCount(g).get());
    }

    private static class EdgesInCondensedCount {
        private final Graph graph;
        int[] topologicalOrder;
        BitSet visited;
        int orderIndex;
        Map<Integer, Integer> vertexToComponent;
        List<List<Integer>> components;
        int iComponent;

        EdgesInCondensedCount(Graph graph) {
            this.graph = graph;
        }

        public int get() {
            topologicalOrder = new int[graph.verticesSize()];
            visited = new BitSet(graph.verticesSize());
            orderIndex = topologicalOrder.length - 1;
            for (int v = 0; v < topologicalOrder.length; v++) {
                if (visited.get(v)) {
                    continue;
                }
                dfsTopological(v);
            }
            vertexToComponent = new HashMap<>();
            components = new ArrayList<>();
            visited.clear();
            for (int v : topologicalOrder) {
                if (visited.get(v)) {
                    continue;
                }
                components.add(new ArrayList<>());
                dfsComponent(v);
                iComponent++;
            }
            Graph g = new Graph(iComponent);
            int edges = 0;
            // or direct?
            for (int i = iComponent - 1; i >= 0; i--) {
                List<Integer> component = components.get(i);
                for (int v : component) {
                    for (int u : graph.getAdjacents(v)) {
                        if (g.addArrow(vertexToComponent.get(v), vertexToComponent.get(u))) {
                            edges++;
                        }
                    }
                }
            }
            return edges;
        }

        private void dfsTopological(int vertex) {
            visited.set(vertex);
            for (int v : graph.getAdjacents(vertex)) {
                if (visited.get(v)) {
                    continue;
                }
                dfsTopological(v);
            }
            topologicalOrder[orderIndex--] = vertex;
        }

        private void dfsComponent(int vertex) {
            visited.set(vertex);
            vertexToComponent.put(vertex, iComponent);
            components.get(iComponent).add(vertex);
            for (int v : graph.getAdjacentsTransposed(vertex)) {
                if (visited.get(v)) {
                    continue;
                }
                dfsComponent(v);
            }
        }
    }

    private static class Graph {
        final List<Set<Integer>> adjacencyList;
        final List<Set<Integer>> transposedList;

        public Graph(int verticesCount) {
            adjacencyList = new ArrayList<>(verticesCount);
            for (int i = 0; i < verticesCount; i++) {
                adjacencyList.add(new HashSet<>());
            }
            transposedList = new ArrayList<>(verticesCount);
            for (int i = 0; i < verticesCount; i++) {
                transposedList.add(new HashSet<>());
            }
        }

        public boolean addArrow(int from, int to) {
            if (from == to) {
                return false;
            }
            boolean added = adjacencyList.get(from).add(to);
            transposedList.get(to).add(from);
            return added;
        }

        public void addEdge(int from, int to) {
            addArrow(from, to);
            addArrow(to, from);
        }

        public Set<Integer> getAdjacents(int vertex) {
            return adjacencyList.get(vertex);
        }

        public Set<Integer> getAdjacentsTransposed(int vertex) {
            return transposedList.get(vertex);
        }

        public int verticesSize() {
            return adjacencyList.size();
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
