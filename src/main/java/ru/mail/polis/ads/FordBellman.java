package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10129800
public class FordBellman {
    private static final int INF = 30000;

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
        int[] d = new int[v + 1];
        Arrays.fill(d, INF);
        d[1] = 0;
        for (int i = 0; i < v - 1; i++) {
            for (Edge edge : edges) {
                if (d[edge.from] != INF) {
                    if (d[edge.from] + edge.weight < d[edge.to]) {
                        d[edge.to] = d[edge.from] + edge.weight;
                    }
                }
            }
        }
        for (int i = 1; i < d.length; i++) {
            out.print(d[i] + " ");
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

    private static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
