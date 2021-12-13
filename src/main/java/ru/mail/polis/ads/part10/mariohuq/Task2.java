package ru.mail.polis.ads.part10.mariohuq;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.eolymp.com/ru/problems/4856">4856. Кратчайший путь</a>
 * <p>
 * <a href="https://www.eolymp.com/ru/submissions/10119339">E-olymp submission</a>
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nVertices = in.nextInt();
        int mEdges = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        Graph g = new Graph(nVertices);
        for (int i = 0; i < mEdges; i++) {
            g.addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        DijkstraResult result = dijkstraMinimalPath(finish, g);
        if (result.distance[start] == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        out.println(result.distance[start]);
        for (int v = start; v != -1; v = result.parent[v]) {
            out.printf("%d ", v + 1);
        }
        out.println();
    }

    private static DijkstraResult dijkstraMinimalPath(int source, Graph g) {
        int[] parent = new int[g.vertices()];
        parent[source] = -1;
        int[] distance = new int[g.vertices()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        IntQueue q = new NaiveQueue(g.vertices(), distance);
        while (!q.isEmpty()) {
            int from = q.remove();
            if (distance[from] == Integer.MAX_VALUE) {
                break;
            }
            for (EdgeTarget target : g.getAdjacents(from)) {
                if (distance[target.to] > distance[from] + target.weight) {
                    distance[target.to] = distance[from] + target.weight;
                    parent[target.to] = from;
                }
            }
        }
        return new DijkstraResult(parent, distance);
    }

    private static class NaiveQueue implements IntQueue {
        private static final int FIRST = 0;
        int size;
        final int[] keys;
        final int pastTheEnd;
        final boolean[] removed;
        NaiveQueue(int count, int[] keys) {
            this.size = count;
            this.keys = keys;
            this.pastTheEnd = FIRST + count;
            removed = new boolean[count];
        }

        @Override
        public int remove() {
            int candidate = INVALID;
            for (int i = FIRST; i < pastTheEnd; i++) {
                if (removed[i]) {
                    continue;
                }
                if (candidate == INVALID || keys[i] < keys[candidate]) {
                    candidate = i;
                }
            }
            if (candidate == -1) {
                return candidate;
            }
            removed[candidate] = true;
            size--;
            return candidate;
        }

        @Override
        public boolean isEmpty() {
            return size <= 0;
        }
    }

    private interface IntQueue {
        int INVALID = -1;
        int remove();
        boolean isEmpty();
    }

    private static class EdgeTarget {
        public final int to;
        public final int weight;

        public EdgeTarget(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class DijkstraResult {
        private final int[] parent;
        private final int[] distance;

        public DijkstraResult(int[] parent, int[] distance) {
            this.parent = parent;
            this.distance = distance;
        }
    }

    private static class Graph {
        List<List<EdgeTarget>> adjacentList;

        public Graph(int nVertices) {
            adjacentList = new ArrayList<>(nVertices);
            for (int i = 0; i < nVertices; i++) {
                adjacentList.add(new ArrayList<>());
            }
        }

        public int vertices() {
            return adjacentList.size();
        }

        public Iterable<? extends EdgeTarget> getAdjacents(int from) {
            return adjacentList.get(from);
        }

        public void addEdge(int from, int to, int weight) {
            adjacentList.get(from).add(new EdgeTarget(to, weight));
            adjacentList.get(to).add(new EdgeTarget(from, weight));
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
