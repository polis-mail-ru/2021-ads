package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {

    private static int[] d;
    private static int[] p;
    private static boolean[] used;
    private static PriorityQueue<Edge> pq;
    private static int sizeV;
    private static List<List<Edge>> adjencyList;

    // Function for Dijkstra's Algorithm
    public static void dijkstra(int from) {
        for (int i = 0; i < sizeV; i++) {
            used[i] = false;
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }

        pq.add(new Edge(from, 0));
        d[from] = 0;

        while (!pq.isEmpty()) {
            int u;
            do {
                if (pq.isEmpty()) {
                    return;
                }
                u = pq.remove().to;
            } while (used[u]);

            used[u] = true;

            if (d[from] == Integer.MAX_VALUE) {
                break;
            }

            for (Edge v : adjencyList.get(u)) {
                int newWeight = d[u] + v.cost;

                if (newWeight < d[v.to]) {
                    d[v.to] = newWeight;
                    p[v.to] = u;
                }

                pq.add(new Edge(v.to, d[v.to]));
            }
        }
    }

    static class Edge {
        public final int to;
        public final int cost;

        public int getCost() {
            return cost;
        }

        public Edge(int node, int weight) {
            this.to = node;
            this.cost = weight;
        }
    }

    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in) throws Exception {
        sizeV = in.nextInt();
        int sizeE = in.nextInt();

        int from = in.nextInt() - 1;
        int to = in.nextInt() - 1;

        d = new int[sizeV];
        p = new int[sizeV];
        used = new boolean[sizeV];
        pq = new PriorityQueue<>(sizeV, Comparator.comparingInt(Edge::getCost));

        adjencyList = new ArrayList<>(sizeV);
        for (int i = 0; i < sizeV; i++) {
            adjencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < sizeE; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            adjencyList.get(u).add(new Edge(v, weight));
            adjencyList.get(v).add(new Edge(u, weight));
        }

        dijkstra(from);

        if (d[to] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(d[to]);

        List<Integer> path = new LinkedList<>();
        int k = to;
        while (k != from) {
            path.add(k);
            k = p[k];
        }
        path.add(from);
        Collections.reverse(path);
        for (Integer integer : path) {
            System.out.print((integer + 1) + " ");
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
        try (PrintWriter ignored = new PrintWriter(System.out)) {
            solve(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
