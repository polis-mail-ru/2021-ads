package ru.mail.polis.ads.part10.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10125300
public final class Week10Test3 {
    private Week10Test3() {
        // Should not be instantiated
    }

    private static final int INF = 30000;

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

        Edge[] graph = new Edge[e];

        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        int[] distance = new int[v + 1];
        distance[1] = 0;
        for (int i = 2; i < distance.length; ++i) {
            distance[i] = INF;
        }

        for (int i = 0; i < v - 1; ++i) {
            for (Edge edge : graph) {
                if (distance[edge.first] == INF) {
                    continue;
                }
                distance[edge.second] = Math.min(distance[edge.second], distance[edge.first] + edge.weight);
            }
        }

        for (int i = 1; i < distance.length; i++) {
            out.printf(distance[i] + " ");
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
