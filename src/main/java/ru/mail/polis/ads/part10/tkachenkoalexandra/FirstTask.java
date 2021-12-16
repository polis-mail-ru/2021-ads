package ru.mail.polis.ads.part10.tkachenkoalexandra;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public final class FirstTask {
    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] nodes = IntStream.range(0, in.nextInt()).toArray();
        Edge[] edges = new Edge[in.nextInt()];
        Arrays.setAll(edges, i -> new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge.weight));
        int cost = 0;
        int amount = nodes.length;
        for (Edge edge : edges) {
            if (nodes[edge.from] != nodes[edge.to]) {
                cost += edge.weight;
                int ex = nodes[edge.to];
                int next = nodes[edge.from];
                IntStream.range(0, nodes.length).filter(j -> nodes[j] == ex).forEach(j -> nodes[j] = next);
                --amount;
                if (amount <= 1) {
                    break;
                }
            }
        }
        out.print(cost);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

