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

public final class DangerRoute {
    private DangerRoute() {
        // Should not be instantiated
    }

    private static int[] trees;

    private static class Edge {
        private final int fromVertex;
        private final int toVertex;
        private final int weightEdge;

        public Edge(int fromVertex, int toVertex, int weightEdge) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weightEdge = weightEdge;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] graph = new Edge[m];
        for (int i = 0; i < m; i++) {
            graph[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        Arrays.sort(graph, Comparator.comparingInt((Edge firstEdge) -> firstEdge.weightEdge));
        trees = new int[n];
        for (int i = 0; i < n; i++) {
            trees[i] = i;
        }
        int maxDanger = Integer.MIN_VALUE;
        int from;
        int to;
        for (int i = 0; i < m; i++) {
            from = graph[i].fromVertex;
            to = graph[i].toVertex;
            if (dsuGet(from) != dsuGet(to)) {
                dsuUnite(from, to);
                if (dsuGet(0) == dsuGet(n - 1)) {
                    maxDanger = graph[i].weightEdge;
                    break;
                }
            }
        }
        out.println(maxDanger);
    }

    private static int dsuGet(int vertex) {
        return (vertex == trees[vertex]) ? vertex : (trees[vertex] = dsuGet(trees[vertex]));
    }

    private static void dsuUnite(int firstTree, int secondTree) {
        firstTree = dsuGet(firstTree);
        secondTree = dsuGet(secondTree);

        if (Math.random() < 0.5) {
            int tmp = firstTree;
            firstTree = secondTree;
            secondTree = tmp;
        }
        if (firstTree != secondTree) {
            trees[firstTree] = secondTree;
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
