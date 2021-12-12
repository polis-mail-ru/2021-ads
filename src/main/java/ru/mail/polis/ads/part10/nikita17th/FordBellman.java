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

public final class FordBellman {
    private FordBellman() {
        // Should not be instantiated
    }

    private static final int INF = 30000;

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
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = INF;
        }
        boolean hasRelax = true;
        distances[0] = 0;
        int newDist;
        while (hasRelax) {
            hasRelax = false;
            for (int j = 0; j < m; j++) {
                if (distances[graph[j].fromVertex] < INF) {
                    newDist = distances[graph[j].fromVertex] + graph[j].weightEdge;
                    if (distances[graph[j].toVertex] > newDist) {
                        distances[graph[j].toVertex] = newDist;
                        hasRelax = true;
                    }
                }
            }
        }
        for (int dist : distances) {
            out.printf("%d ", dist);
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
