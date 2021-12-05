package ru.mail.polis.ads.part10.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10047230
public final class Week10Test1 {
    private Week10Test1() {
        // Should not be instantiated
    }

    private static class Edge {

        private int first;
        private int second;
        private int weight;

        public Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }

    private static int[] parent;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int countSet = n;

        parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        Queue<Edge> queue = new PriorityQueue<>(n, (edge1, edge2) -> edge1.weight - edge2.weight);

        for (int i = 0; i < m; i++) {
            int v1 = in.nextInt() - 1;
            int v2 = in.nextInt() - 1;
            int weight = in.nextInt();

            queue.add(new Edge(v1, v2, weight));
        }

        long minSum = 0;

        while (countSet != 1) {
            Edge edge = queue.poll();
            if (findSet(edge.first) != findSet(edge.second)) {
                unionSet(edge.first, edge.second);
                minSum += edge.weight;
                countSet--;
            }
        }

        out.println(minSum);
    }

    private static void unionSet(int firstNode, int secondNode) {
        firstNode = findSet(firstNode);
        secondNode = findSet(secondNode);
        if (firstNode != secondNode) {
            parent[secondNode] = firstNode;
        }
    }

    private static int findSet(int idNode) {
        if (idNode == parent[idNode]) {
            return idNode;
        }
        return parent[idNode] = findSet(parent[idNode]);
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
