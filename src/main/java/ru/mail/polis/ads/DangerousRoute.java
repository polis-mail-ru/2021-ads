package ru.mail.polis.ads;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

//https://www.eolymp.com/ru/submissions/10130379
public class DangerousRoute {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int dangerLevel = in.nextInt();
            edges[i] = new Edge(from, to, dangerLevel);
        }

        Arrays.sort(edges);
        int[] graph = IntStream.range(0, v + 1).toArray();
        int safestIndex = 0;
        for (int i = 0; i < e; i++) {
            union(graph, edges[i].from, edges[i].to);
            if (find(graph, 1) == find(graph, v)) {
                safestIndex = i;
                break;
            }
        }
        out.println(edges[safestIndex].weight);
    }

    private static int find(int[] graph, int v) {
        if (v == graph[v]) {
            return v;
        }
        graph[v] = find(graph, graph[v]);
        return graph[v];
    }

    private static void union(int[] graph, int e1, int e2) {
        e1 = find(graph, e1);
        e2 = find(graph, e2);
        graph[e1] = e2;
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
