package ru.mail.polis.ads.part9.nikita17th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class ShortestWay {
    private ShortestWay() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        int a;
        int b;
        boolean[] used = new boolean[n];
        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int[] parents = new int[n];
        int[] dist = new int[n];
        used[start] = true;
        parents[start] = -1;
        int vertex;
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            for (int to : graph[vertex]) {
                if (!used[to]) {
                    used[to] = true;
                    queue.add(to);
                    dist[to] = dist[vertex] + 1;
                    parents[to] = vertex;
                }
            }
        }

        if (!used[finish]) {
            out.println(-1);
            return;
        }

        out.println(dist[finish]);
        List<Integer> path = new ArrayList<>();
        for (int v = finish; v != -1; v = parents[v]) {
            path.add(v + 1);
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            out.printf("%d ", path.get(i));
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
