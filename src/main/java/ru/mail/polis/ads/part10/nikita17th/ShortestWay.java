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

public final class ShortestWay {
    private ShortestWay() {
        // Should not be instantiated
    }

    private static class Edge {
        private final int toVertex;
        private final int weightEdge;

        public Edge(int toVertex, int weightEdge) {
            this.toVertex = toVertex;
            this.weightEdge = weightEdge;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        int from;
        int to;
        int weight;
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        int[] parents = new int[n];
        distances[start] = 0;
        boolean[] used = new boolean[n];


        for (int i = 0; i < n; i++) {
            from = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (from == -1 || distances[j] < distances[from])) {
                    from = j;
                }
            }
            if (distances[from] == Integer.MAX_VALUE) {
                break;
            }
            used[from] = true;

            for (Edge toEdge : graph[from]) {
                to = toEdge.toVertex;
                weight = toEdge.weightEdge;
                if (distances[from] + weight < distances[to]) {
                    distances[to] = distances[from] + weight;
                    parents[to] = from;
                }
            }
        }
        if (distances[finish] == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }

        out.println(distances[finish]);
        List<Integer> path = new ArrayList<>();
        for (int v = finish; v != start; v = parents[v]) {
            path.add(v);
        }
        ListIterator<Integer> iter = path.listIterator(path.size());
        out.printf("%d ", start + 1);
        while (iter.hasPrevious()) {
            out.printf("%d ", iter.previous() + 1);
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
