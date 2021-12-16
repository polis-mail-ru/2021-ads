package ru.mail.polis.ads;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

//https://www.eolymp.com/ru/submissions/10143545
public class MinimalFrame {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new Edge(from, to, weight);
        }
        Arrays.sort(edges);
        int[] graph = IntStream.range(0, v + 1).toArray();
        int resWeight = 0;
        for (Edge edge : edges) {
            if (graph[edge.from] == graph[edge.to]) {
                continue;
            }
            int oldI = graph[edge.to];
            int newI = graph[edge.from];
            resWeight += edge.weight;
            for (int i = 1; i <= v; i++) {
                if (graph[i] == oldI) {
                    graph[i] = newI;
                }
            }
        }
        out.println(resWeight);
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

    private static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        public static PrintWriter createPrintWriterForLocalTests() {
            return new PrintWriter(System.out, true);
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}