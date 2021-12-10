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
public final class CycleInGraph {

    private static int[] countEdges;
    private static int[] enterTime;
    private static int[] fup;
    private static boolean[] used;
    private static int timer;
    private static List<Integer>[] graph;

    private CycleInGraph() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a;
        int b;
        countEdges = new int[n];
        used = new boolean[n];
        fup = new int[n];
        enterTime = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
            countEdges[a]++;
            countEdges[b]++;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i, -1);
            }
        }
        for (int i = 0; i < countEdges.length; i++) {
            if (countEdges[i] != 0) {
                out.printf("Yes\n%d", i + 1);
                return;
            }
        }
        out.println("No");
    }

    private static void dfs(int vertex, int parent) {
        used[vertex] = true;
        enterTime[vertex] = timer;
        fup[vertex] = timer++;
        for (int to : graph[vertex]) {
            if (to != parent) {
                if (used[to]) {
                    fup[vertex] = Math.min(fup[vertex], enterTime[to]);
                } else {
                    dfs(to, vertex);
                    fup[vertex] = Math.min(fup[vertex], fup[to]);
                    if (fup[to] > enterTime[vertex]) {
                        countEdges[vertex]--;
                        countEdges[to]--;
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