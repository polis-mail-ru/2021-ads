package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

//https://www.eolymp.com/ru/submissions/10076692
public class TopologicalSort {
    private static final byte WHITE = 0;
    private static final byte GREY = 1;
    private static final byte BLACK = 2;

    private static Set<Integer>[] adjacencies;
    private static final List<Integer> ans = new ArrayList<>();
    private static boolean hasCycle;
    private static byte[] color;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        color = new byte[v + 1];
        adjacencies = new HashSet[v + 1];

        for (int i = 1; i <= v; i++) {
            adjacencies[i] = new HashSet<>();
        }
        for (int i = 1; i <= e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            adjacencies[from].add(to);
        }
        for (int i = 1; i <= v; i++) {
            if (color[i] == WHITE) {
                dfs(i);
            }
        }
        if (hasCycle) {
            out.println("-1");
            return;
        }
        for (int i = ans.size() - 1; i >= 0; i--) {
            out.print(ans.get(i) + " ");
        }
    }

    private static void dfs(int v) {
        color[v] = GREY;
        for (Integer to : adjacencies[v]) {
            if (color[to] == GREY) {
                hasCycle = true;
                return;
            }
            if (color[to] == WHITE) {
                dfs(to);
            }
        }
        color[v] = BLACK;
        ans.add(v);
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
