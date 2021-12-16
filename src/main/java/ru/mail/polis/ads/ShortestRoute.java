package ru.mail.polis.ads;

import java.io.*;
import java.util.*;
//https://www.eolymp.com/ru/submissions/10130159
public class ShortestRoute {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Set<Vertex>[] adjacencies = new Set[v + 1];
        for (int i = 1; i <= v; i++) {
            adjacencies[i] = new HashSet<>();
        }
        for (int i = 1; i <= e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            adjacencies[from].add(new Vertex(to, weight));
            adjacencies[to].add(new Vertex(from, weight));
        }

        boolean[] used = new boolean[v + 1];
        int[] dist = new int[v + 1];
        int[] prev = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[a] = 0;
        Queue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(a, 0));
        while (!queue.isEmpty()) {
            Vertex from = queue.poll();
            if (used[from.vertex - 1]) {
                continue;
            }
            used[from.vertex - 1] = true;
            for (Vertex to : adjacencies[from.vertex]) {
                int weight = dist[from.vertex] + to.weight;
                if (dist[to.vertex] > weight) {
                    dist[to.vertex] = weight;
                    queue.add(new Vertex(to.vertex, dist[to.vertex]));
                    prev[to.vertex] = from.vertex;
                }
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int from = b;
        while (prev[from] != 0) {
            stack.push(from);
            from = prev[from];
        }
        out.println(dist[b]);
        stack.push(a);
        for (Integer i : stack) {
            out.print(i + " ");
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

    private static class Vertex implements Comparable<Vertex> {
        public int vertex;
        public int weight;

        public Vertex(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
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
