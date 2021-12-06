package ru.mail.polis.ads.part9.mariohuq;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.e-olymp.com/ru/problems/1948">1948. Топологическая сортировка</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10059424">E-olymp submission</a>
 */
public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        Graph g = new Graph(nVertices);
        for (int i = 0; i < mEdges; i++) {
            g.addArrow(in.nextInt() - 1, in.nextInt() - 1);
        }
        int[] answer = new TopSort(g).get();
        if (answer == null) {
            out.println(-1);
            return;
        }
        for (int vertex : answer) {
            out.printf("%d ", vertex + 1);
        }
        out.println();
    }

    private static class TopSort {
        static final int WHITE = 0;
        static final int GRAY = 1;
        static final int BLACK = 2;

        private final Graph graph;
        int[] answer;
        int[] colors;
        int answerIndex;
        boolean cycleFound;

        TopSort(Graph graph) {
            this.graph = graph;
        }

        public int[] get() {
            answer = new int[graph.verticesSize()];
            colors = new int[graph.verticesSize()];
            answerIndex = answer.length - 1;
            for (int v = 0; v < answer.length; v++) {
                if (colors[v] != WHITE) {
                    continue;
                }
                dfs(v);
                if (cycleFound) {
                    return null;
                }
            }
            return answer;
        }

        private void dfs(int vertex) {
            colors[vertex] = GRAY;
            for (int v : graph.getAdjacents(vertex)) {
                switch (colors[v]) {
                    case WHITE:
                        dfs(v);
                        if (cycleFound) {
                            return;
                        }
                        break;
                    case GRAY:
                        // cycle from v to vertex
                        cycleFound = true;
                        return;
                }
            }
            colors[vertex] = BLACK;
            answer[answerIndex--] = vertex;
        }
    }

    private static class Graph {
        final List<List<Integer>> adjacencyList;

        public Graph(int verticesCount) {
            adjacencyList = new ArrayList<>(verticesCount);
            for (int i = 0; i < verticesCount; i++) {
                adjacencyList.add(new LinkedList<>());
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
