package ru.mail.polis.ads.part9.tkachenkoalexandra;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class ThirdTask {
    private static final int MAX = 100_000;

    private static int[] parent;
    private static int[] distance;
    private static List<Integer>[] adjacencyList;

    private static void solve(final FastScanner in, final PrintWriter out) {
        adjacencyList = new ArrayList[checkCount(in.nextInt())];
        IntStream.range(0, adjacencyList.length).forEach(i -> adjacencyList[i] = new ArrayList<>());
        int edges = in.nextInt();
        int start = in.nextInt() - 1;
        int end = in.nextInt() - 1;
        IntStream.range(0, edges).map(i -> in.nextInt() - 1)
                .forEach(fist -> {
                    int second = in.nextInt() - 1;
                    adjacencyList[fist].add(second);
                    adjacencyList[second].add(fist);
                });

        parent = new int[adjacencyList.length];
        Arrays.fill(parent, -1);
        distance = new int[adjacencyList.length];
        Arrays.fill(distance, -1);
        bfs(start);
        if (parent[end] != -1) {
            out.println(distance[end]);
            int[] path = new int[adjacencyList.length];
            int depth = 0;
            path[depth++] = end;
            while (parent[end] != -1) {
                end = parent[end];
                path[depth++] = end;
            }
            for (int i = 0; i < depth; ++i) {
                out.print((path[i] + 1) + " ");
            }
        } else {
            out.println(parent[end]);
        }
    }

    private static void bfs(int vertex) {
        distance[vertex] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int edge : adjacencyList[node]) {
                if (distance[edge] == -1) {
                    distance[edge] = distance[node] + 1;
                    parent[edge] = node;
                    queue.add(edge);
                }
            }
        }
    }

    private static int checkCount(int n) {
        if ((n < 1) || (n > MAX)) {
            throw new IllegalArgumentException("The entered number does not match the condition.\n");
        }
        return n;
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
