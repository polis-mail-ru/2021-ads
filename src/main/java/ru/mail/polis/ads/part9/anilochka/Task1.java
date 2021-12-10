package ru.mail.polis.ads.part9.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static ArrayList<Integer>[] graph;
    private static int[] d;
    private static Deque<Integer> q;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        graph = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            if (graph[first] == null) {
                graph[first] = new ArrayList<>();
            }
            if (graph[second] == null) {
                graph[second] = new ArrayList<>();
            }
            graph[first].add(second);
            graph[second].add(first);
        }
        bfs(a);
        if (d[b] == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        Stack<Integer> path = new Stack<>();
        path.push(b);
        while (path.peek() != a) {
            int tmp = graph[path.peek()].get(0);
            for (int el : graph[path.peek()]) {
                if (d[el] < d[tmp]) {
                    tmp = el;
                }
            }
            path.push(tmp);
        }
        System.out.println(path.size() - 1);
        while (!path.isEmpty()) {
            System.out.print((path.pop() + 1) + " ");
        }
    }

    private static void bfs(int a) {
        d = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[a] = 0;
        q = new ArrayDeque<>();
        q.add(a);
        while (!q.isEmpty()) {
            int u = q.pop();
            if (graph[u] != null) {
                for (int el : graph[u]) {
                    if (d[el] == Integer.MAX_VALUE) {
                        d[el] = d[u] + 1;
                        q.add(el);
                    }
                }
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
