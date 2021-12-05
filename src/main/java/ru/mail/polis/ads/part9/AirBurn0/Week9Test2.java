package ru.mail.polis.ads.part9.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10044796
public final class Week9Test2 {

    private final static byte WHITE = 0;

    private final static byte GRAY = 1;

    private final static byte BLACK = 2;

    private Week9Test2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt(), e = in.nextInt();

        List<Integer>[] list = new ArrayList[v];

        for (int i = 0; i < v; ++i) {
            list[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < e; ++i) {
            list[in.nextInt() - 1].add(in.nextInt() - 1);
        }

        byte[] color = new byte[v];
        Deque<Integer> ans = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (color[i] == WHITE) {
                dfs(list, color, ans, i);
            }
        }
        while (!ans.isEmpty()) {
            out.print(ans.poll() + 1 + " ");
        }
    }

    private static void dfs(List<Integer>[] graph, byte[] color, Deque<Integer> ans, Integer start) {
        color[start] = GRAY;
        for (Integer v : graph[start]) {
            if (color[v] == WHITE) {
                dfs(graph, color, ans, v);
            }
            if (color[v] == GRAY) {
                // быстрый выход
                System.out.println(-1);
                System.exit(0);
            }
        }
        color[start] = BLACK;
        ans.push(start);
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
