package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DangerousPath {
    static Edge[] edges;
    static int n;
    static int[] mas;
    static int m;

    static int parent(int n) {
        if (n == mas[n]) return n;
        mas[n] = parent(mas[n]);
        return mas[n];
    }

    static void union(int x, int y) {
        int x1 = parent(x), y1 = parent(y);
        mas[x1] = y1;
    }

    // https://www.eolymp.com/ru/submissions/10097657#results
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        mas = new int[n + 1];
        edges = new Edge[m];

        for (int i = 1; i <= n; i++) {
            mas[i] = i;
        }

        readGraph(in);
        Arrays.sort(edges);

        int res = 0;
        for (int i = 0; i < m; i++) {
            union(edges[i].from, edges[i].to);
            if (parent(1) == parent(n)) {
                res = i;
                break;
            }
        }

        out.println(edges[res].danger);
    }

    private static void readGraph(FastScanner in) {
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int danger = in.nextInt();
            edges[i] = new Edge(a, b, danger);
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

    static class Edge implements Comparable<Edge> {
        public int from, to, danger;

        public Edge(int from, int to, int danger) {
            this.from = from;
            this.to = to;
            this.danger = danger;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(danger, o.danger);
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
