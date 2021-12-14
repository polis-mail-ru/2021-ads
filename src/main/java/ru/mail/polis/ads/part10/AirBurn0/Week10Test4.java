package ru.mail.polis.ads.part10.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10126638
public final class Week10Test4 {

    private Week10Test4() {
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();

        int[] graph = new int[v];
        for (int i = 0; i < v; ++i) {
            graph[i] = i;
        }

        Queue<Edge> queue = new PriorityQueue<>(v, (e1, e2) -> e1.weight - e2.weight);

        for (int i = 0; i < e; ++i) {
            queue.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }

        --v;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            unionSet(graph, edge.first, edge.second);
            if (findSet(graph, v) == 0) {
                out.println(edge.weight);
                break;
            }
        }

    }

    private static void unionSet(int[] graph, int e1, int e2) {
        e1 = findSet(graph, e1);
        e2 = findSet(graph, e2);
        if (e1 > e2) {
            graph[e1] = e2;
        } else {
            graph[e2] = e1;
        }
    }

    private static int findSet(int[] graph, int v) {
        if (v == graph[v]) {
            return v;
        }
        return graph[v] = findSet(graph, graph[v]);
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
