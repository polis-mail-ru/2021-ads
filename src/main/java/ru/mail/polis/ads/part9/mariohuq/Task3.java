package ru.mail.polis.ads.part9.mariohuq;

import java.io.*;
import java.util.*;

/**
 * <a href="https://www.e-olymp.com/ru/problems/2022">2022. Циклы в графе</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10061827">E-olymp submission</a>
 */
public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(nVertices);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1);
        }
        int answer = new MinOnCycle(g).get();
        if (answer == -1) {
            out.println("No");
            return;
        }
        out.println("Yes");
        out.println(answer + 1);
    }

    private static class MinOnCycle {
        static final int WHITE = 0;
        static final int GRAY = 1;
        static final int BLACK = 2;

        private final Graph graph;
        int answer = -1;
        int[] colors;
        int[] parents;

        MinOnCycle(Graph graph) {
            this.graph = graph;
        }

        public int get() {
            answer = -1;
            colors = new int[graph.verticesSize()];
            parents = new int[graph.verticesSize()];
            Arrays.fill(parents, -1);
            graph.sortAdjacents();
            for (int root = 0; root < colors.length; root++) {
                if (colors[root] != WHITE) {
                    continue;
                }
                parents[root] = -1;
                dfs(root);
                if (answer != -1) {
                    return answer;
                }
            }
            return answer;
        }

        private void dfs(int parent) {
            colors[parent] = GRAY;
            for (int child : graph.getAdjacents(parent)) {
                switch (colors[child]) {
                    case WHITE:
                        parents[child] = parent;
                        dfs(child);
                        break;
                    case GRAY:
                        // don't treat (grandParent = child) -> (parent) -> (child) as a cycle
                        if (child == parents[parent]) {
                            continue;
                        }
                        // found cycle (child) ->...-> (parent) -> (child)
                        answer = answer == -1 ? child : Math.min(child, answer);
                        for (int v = parent; v != child; v = parents[v]) {
                            answer = Math.min(v, answer);
                        }
                        colors[parent] = BLACK;
                        return;
                }
            }
            colors[parent] = BLACK;
        }
    }

    private static class Graph {
        final List<List<Integer>> adjacencyList;

        public Graph(int verticesCount) {
            adjacencyList = new ArrayList<>(verticesCount);
            for (int i = 0; i < verticesCount; i++) {
                // not LinkedList, like in Tasks 1 and 2
                adjacencyList.add(new ArrayList<>(2));
            }
        }

        public void addArrow(int from, int to) {
            adjacencyList.get(from).add(to);
        }

        public void addEdge(int from, int to) {
            addArrow(from, to);
            addArrow(to, from);
        }

        public List<Integer> getAdjacents(int vertex) {
            return adjacencyList.get(vertex);
        }

        public int verticesSize() {
            return adjacencyList.size();
        }

        public void sortAdjacents() {
            for (List<Integer> adjacent : adjacencyList) {
                adjacent.sort(null);
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
