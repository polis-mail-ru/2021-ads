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
public final class TopologicalSort {

    private static int[] color;
    private static List<Integer>[] graph;
    private static final List<Integer> ans = new ArrayList<>();

    private TopologicalSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        color = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            graph[in.nextInt() - 1].add(in.nextInt() - 1);
        }

        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && dfs(i)) {
                out.println(-1);
            }
        }
        ListIterator<Integer> iter = ans.listIterator(ans.size());
        while (iter.hasPrevious()) {
            out.printf("%d ", iter.previous() + 1);
        }
    }

    private static boolean dfs(int vertex) {
        color[vertex] = 1;
        for (int to : graph[vertex]) {
            if (color[to] == 0) {
                dfs(to);
            }
            if (color[to] == 1) {
                return true;
            }
        }
        ans.add(vertex);
        color[vertex] = 2;
        return false;
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
