package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ShortestPath {
    static Map<Integer, List<Integer>> edges;
    static List<Integer> path, stack;
    static boolean found;
    static boolean[] visited;
    static int n;
    static int m;
    static int from, to, count = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        edges = new HashMap<>();
        path = new ArrayList<>();
        stack = new ArrayList<>();
        visited = new boolean[n + 1];
        Arrays.fill(visited, false);

        readGraph(in);

        if (edges.get(from).isEmpty() || edges.get(to).isEmpty()) {
            out.print("-1");
            return;
        }


    }


    private static int bfs(int beg, int end) {
        count++;
        if (beg == end) {
            found = true;
            return 1;
        } else {
            for (Integer cur : edges.getOrDefault(beg, Collections.emptyList())) {
                if (!visited[cur]) {
                    stack.add(cur);
                    path.add(beg);
                    visited[cur] = true;
                }
            }
        }
        return 0;
    }

    private static void readGraph(FastScanner in) {
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (i == 0) {
                from = a;
                to = b;
            }
            if (a != b) {
                edges.compute(a, (k, v) -> {
                    if (v == null) {
                        v = new LinkedList<>();
                    }
                    v.add(b);
                    return v;
                });
            }
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
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
