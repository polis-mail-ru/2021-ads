package ru.mail.polis.ads.part9.tkachenkoalexandra;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public final class SecondTask {
    private static final int MAX = 100_000;

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private static int[] parent;
    private static boolean[] cycle;
    private static Color[] color;
    private static List<Integer>[] adjacencyList;

    private static void solve(final FastScanner in, final PrintWriter out) {
        adjacencyList = new ArrayList[checkCount(in.nextInt())];
        IntStream.range(0, adjacencyList.length).forEach(i -> adjacencyList[i] = new ArrayList<>());
        cycle = new boolean[adjacencyList.length];
        color = new Color[adjacencyList.length];
        Arrays.fill(color, Color.WHITE);
        parent = new int[adjacencyList.length];
        IntStream.range(0, in.nextInt()).map(i -> in.nextInt() - 1)
                .forEach(fist -> {
                    int second = in.nextInt() - 1;
                    adjacencyList[fist].add(second);
                    adjacencyList[second].add(fist);
                });
        parent = new int[adjacencyList.length];
        cycle = new boolean[adjacencyList.length];
        color = new Color[adjacencyList.length];
        Arrays.fill(color, Color.WHITE);
        for (int i = 0; i < adjacencyList.length; ++i) {
            if (color[i] == Color.WHITE) {
                dfs(i);
            }
        }
        for (int i = 0; i < adjacencyList.length; ++i) {
            if (cycle[i]) {
                out.println("Yes");
                out.println(i + 1);
                return;
            }
        }
        out.println("No");
    }

    private static void dfs(int v) {
        color[v] = Color.GREY;
        for (Integer i : adjacencyList[v]) {
            if (color[i] == Color.BLACK || i == parent[v]) {
                continue;
            }
            if (color[i] == Color.GREY) {
                cycle[i] = true;
                int j = v;
                while (j != i) {
                    cycle[j] = true;
                    j = parent[j];
                }
            } else if (color[i] == Color.WHITE) {
                parent[i] = v;
                dfs(i);
            }
        }
        color[v] = Color.BLACK;
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
