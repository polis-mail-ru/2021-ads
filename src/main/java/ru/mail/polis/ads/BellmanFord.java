package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BellmanFord {
    static int n, m;
    static long[] v;
    static Edge[] edges;

    // https://www.eolymp.com/ru/submissions/10070847
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        v = new long[n + 1];
        edges = new Edge[m];
        Arrays.fill(v, Long.MAX_VALUE);
        v[1] = 0;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            edges[i] = new Edge(a, b, w);
        }

        fordBellman();

        for (int i = 1; i < v.length; i++) {
            if (i != 1)
                out.print(" ");
            if (v[i] == Long.MAX_VALUE)
                out.print(30000);
            else
                out.print(v[i]);
        }
        out.println();
    }

    private static void fordBellman() {

        Edge current;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                current = edges[i];
                if (v[current.from] < Long.MAX_VALUE) {
                    v[current.to] = Math.min(v[current.from] + current.weight, v[current.to]);
                }
            }
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
        public int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
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
