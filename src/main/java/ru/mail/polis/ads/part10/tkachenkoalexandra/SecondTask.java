package ru.mail.polis.ads.part10.tkachenkoalexandra;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class SecondTask {
    public static final int INFINITY = Integer.MAX_VALUE;

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
        int[] path = new int[in.nextInt()];
        Edge[] edges = new Edge[in.nextInt()];
        Arrays.setAll(edges, i -> new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        Arrays.fill(path, INFINITY);
        path[0] = 0;
        int amount = path.length;
        IntStream.range(0, amount).mapToObj(i -> Arrays.stream(edges)).flatMap(Function.identity())
                .filter(edge -> path[edge.from] < INFINITY && path[edge.to] > path[edge.from] + edge.weight)
                .forEach(edge -> path[edge.to] = path[edge.from] + edge.weight);
        Arrays.stream(path).forEach(dist -> out.print(dist + " "));
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
