package ru.mail.polis.ads.part9.tkachenkoalexandra;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public final class FirstTask {
    private static final int MAX = 100_000;

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private static Color[] colors;
    private static boolean notDAG;
    private static final Deque<Integer> deque = new ArrayDeque<>();
    private static List<Integer>[] adjacencyList;

    private FirstTask() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        adjacencyList = new ArrayList[checkCount(in.nextInt())];
        IntStream.range(0, adjacencyList.length).forEach(i -> adjacencyList[i] = new ArrayList<>());
        IntStream.range(0, checkCount(in.nextInt())).forEach(i -> adjacencyList[in.nextInt() - 1].add(in.nextInt() - 1));
        colors = new Color[adjacencyList.length];
        Arrays.fill(colors, Color.WHITE);
        boolean[] visited = new boolean[adjacencyList.length];
        for (int i = 0; i < adjacencyList.length; ++i) {
            if (colors[i] == Color.WHITE) {
                dfs(i);
                if (notDAG) {
                    out.print(-1);
                    return;
                }
            }
            if (!visited[i]) {
                sort(i, visited, deque);
            }
        }
        while (!deque.isEmpty()) {
            out.print((deque.pop() + 1) + " ");
        }
    }

    private static void dfs(int vertex) {
        colors[vertex] = Color.GREY;
        for (Integer node : adjacencyList[vertex]) {
            if (colors[node] == Color.WHITE) {
                dfs(node);
            } else if (colors[node] == Color.GREY) {
                notDAG = true;
                break;
            }
        }
        colors[vertex] = Color.BLACK;
    }

    private static void sort(int vertex, boolean[] discovered, Deque<Integer> deque) {
        discovered[vertex] = true;
        for (Integer node : adjacencyList[vertex]) {
            if (!discovered[node]) {
                sort(node, discovered, deque);
            }
        }
        deque.push(vertex);
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

