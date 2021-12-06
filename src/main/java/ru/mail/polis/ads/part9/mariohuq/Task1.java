package ru.mail.polis.ads.part9.mariohuq;

import java.io.*;
import java.util.*;

/**
 * <a href="https://www.e-olymp.com/ru/problems/4853">4853. Кратчайший путь</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10058367">E-olymp submission</a>
 */
public class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        int aStart = in.nextInt() - 1;
        int bEnd = in.nextInt() - 1;
        Graph g = new Graph(nVertices);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1);
        }
        int[] answer = solve(g, aStart, bEnd);
        out.println(answer.length - 1);
        if (answer.length == 0) {
            return;
        }
        for (int vertex : answer) {
            out.printf("%d ", vertex + 1);
        }
        out.println();
    }

    private static int[] solve(Graph g, int aStart, int bEnd) {
        final Queue<Integer> queue = new ArrayDeque<>(g.verticesSize());
        queue.add(aStart);
        final int[] parents = new int[g.verticesSize()];
        Arrays.fill(parents, -1);
        parents[aStart] = aStart;
        final int[] distances = new int[g.verticesSize()];
        Arrays.fill(distances, -1);
        distances[aStart] = 0;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v : g.getAdjacents(u)) {
                if (parents[v] == -1) {
                    parents[v] = u;
                    distances[v] = distances[u] + 1;
                    if (v == bEnd) {
                        queue.clear();
                        break;
                    }
                    queue.add(v);
                }
            }
        }
        final int[] result = new int[1 + distances[bEnd]];
        for (int i = result.length - 1, current = bEnd; i >= 0; i--, current = parents[current]) {
            result[i] = current;
        }
        return result;
    }

    private static class Graph {
        final List<List<Integer>> adjacencyList;
        int startVertex;

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

        public void setStartVertex(int startVertex) {
            this.startVertex = startVertex;
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
