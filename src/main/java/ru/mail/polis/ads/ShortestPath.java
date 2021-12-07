package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ShortestPath {
    static Map<Integer, List<AbstractMap.SimpleEntry<Integer, Integer>>> edges;
    static List<Integer> path;
    static Deque<Integer> stack;
    static int n;
    static int m;
    static int from, to;

    // https://www.eolymp.com/ru/submissions/10070410
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        from = in.nextInt();
        to = in.nextInt();

        edges = new HashMap<>();
        path = new ArrayList<>();
        stack = new LinkedList<>();
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;
        int[] p = new int[n + 1];
        Arrays.fill(p, -1);

        readGraph(in);
        stack.addLast(from);

        while (!stack.isEmpty()) {
            int v = stack.pollFirst();
            for (Map.Entry<Integer, Integer> u : edges.getOrDefault(v, Collections.emptyList())) {
                if (dist[u.getKey()] > dist[v] + u.getValue()) {
                    p[u.getKey()] = v;
                    dist[u.getKey()] = dist[v] + u.getValue();
                    stack.addLast(u.getKey());
                }
            }
        }

        if (dist[to] == Integer.MAX_VALUE) {
            out.print("-1");
            return;
        }

        long res = dist[to];
        while (to != -1) {
            path.add(to);
            to = p[to];
        }

        Collections.reverse(path);

        out.println(res);
        path.forEach(a -> out.print(a + " "));

    }

    private static void readGraph(FastScanner in) {
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            if (a != b) {
                edges.compute(a, (k, v) -> {
                    if (v == null) {
                        v = new LinkedList<>();
                    }
                    v.add(new AbstractMap.SimpleEntry<>(b, w));
                    return v;
                });
                edges.compute(b, (k, v) -> {
                    if (v == null) {
                        v = new LinkedList<>();
                    }
                    v.add(new AbstractMap.SimpleEntry<>(a, w));
                    return v;
                });
            }
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
