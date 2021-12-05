package ru.mail.polis.ads.part9.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10044739
public final class Week9Test4 {
    private Week9Test4() {
        // Should not be instantiated
    }

    private static int level;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt(), e = in.nextInt();

        List<Integer>[] graph = new ArrayList[v];
        List<Integer>[] graphRev = new ArrayList[v];
        int[] vertexes = new int[v];
        int[] order = new int[v];

        for (int i = 0; i < v; ++i) {
            graph[i] = new ArrayList<>();
            graphRev[i] = new ArrayList<>();
        }

        int temp1, temp2;

        for (int i = 0; i < e; ++i) {
            temp1 = in.nextInt() - 1;
            temp2 = in.nextInt() - 1;

            graph[temp1].add(temp2);
            graphRev[temp2].add(temp1);
        }

        boolean visited[] = new boolean[v];
        topSort(graph, order, v);

        temp1 = 0;
        for (Integer w : order) {
            if (!visited[w]) {
                dfsSub(graphRev, vertexes, w, visited, ++temp1);
            }
        }
        graphRev = new ArrayList[temp1 + 1];

        for (int i = 0; i < temp1 + 1; ++i) {
            graphRev[i] = new ArrayList<>();
        }

        int count = 0;
        for (int i = 0; i < v; ++i) {
            for (Integer w : graph[i]) {
                temp1 = vertexes[i];
                temp2 = vertexes[w];
                if (temp1 != temp2 && !graphRev[temp1].contains(temp2)) {
                    ++count;
                    graphRev[temp1].add(temp2);
                    graphRev[temp2].add(temp1);
                }
            }
        }

        out.println(count);
    }

    static void topSort(List<Integer>[] graph, int[] order, int n) {
        boolean visited[] = new boolean[n];
        level = n - 1;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(graph, order, i, visited);
            }
        }
    }

    static void dfs(List<Integer>[] graph, int[] order, int v, boolean visited[]) {
        visited[v] = true;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                dfs(graph, order, w, visited);
            }
        }
        order[level--] = v;
    }

    static void dfsSub(List<Integer>[] graph, int[] vertexes, int v, boolean visited[], int sub) {
        visited[v] = true;
        vertexes[v] = sub;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                dfsSub(graph, vertexes, w, visited, sub);
                vertexes[w] = sub;
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
