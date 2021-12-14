package ru.mail.polis.ads.part10.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10127321
public final class Week10Test5 {
    private Week10Test5() {
        // Should not be instantiated
    }

    private static class Edge {

        private int first;
        private int second;

        public Edge(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static int level = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt();
        final int e = in.nextInt();

        int[] array1 = new int[v];
        int[] array2 = new int[v];
        List<Edge>[] graph = new ArrayList[v];
        boolean[] isBridge = new boolean[e];

        for (int i = 0; i < v; ++i) {
            graph[i] = new ArrayList<>();
        }

        int temp1, temp2;
        for (int i = 0; i < e; ++i) {
            temp1 = in.nextInt() - 1;
            temp2 = in.nextInt() - 1;
            graph[temp1].add(new Edge(temp2, i));
            graph[temp2].add(new Edge(temp1, i));
        }

        temp1 = find(graph, array1, array2, isBridge, v);

        out.println(temp1);
        for (int i = 0; i < e && temp1 > 0; ++i) {
            if (isBridge[i]) {
                out.print((i + 1) + " ");
                --temp1;
            }
        }
    }

    private static int find(List<Edge>[] graph, int[] array1, int[] array2, boolean[] isBridge, int n) {
        int bridge = 0;
        for (int i = 0; i < n; ++i) {
            if (array1[i] == 0) {
                bridge += dfs(graph, array1, array2, isBridge, i, -1);
            }
        }
        return bridge;
    }

    private static int dfs(List<Edge>[] graph, int[] array1, int[] array2, boolean[] isBridge, int edgeId, int prev) {
        array1[edgeId] = array2[edgeId] = ++level;
        int bridge = 0;
        for (Edge link : graph[edgeId]) {
            if (link.second == prev) {
                continue;
            }
            if (array1[link.first] == 0) {
                bridge += dfs(graph, array1, array2, isBridge, link.first, link.second);
                array2[edgeId] = Math.min(array2[edgeId], array2[link.first]);
            } else {
                array2[edgeId] = Math.min(array2[edgeId], array1[link.first]);
            }
        }
        if (prev != -1 && array2[edgeId] == array1[edgeId]) {
            bridge++;
            isBridge[prev] = true;
        }
        return bridge;
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
