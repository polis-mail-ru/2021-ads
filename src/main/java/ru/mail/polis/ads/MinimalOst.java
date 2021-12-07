package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinimalOst {
    static int n, m;
    static long result = 0;
    static int[] v, color;
    static Edge[] edges;

    // https://www.eolymp.com/ru/submissions/10070354
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        v = new int[n + 1];
        color = new int[n + 1];
        edges = new Edge[m];
        Arrays.fill(v, 0);
        for (int i = 1; i < n + 1; i++) {
            color[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            edges[i] = new Edge(a, b, w);
        }
        Arrays.sort(edges);

        kruskal();

        out.print(result);

    }

    private static void kruskal() {
        int remain = n;
        Edge current;
        for (int i = 0; i < m; i++) {
            current = edges[i];
            if (color[current.from] != color[current.to]) {
                result += current.weight;
                int oldColor = color[current.to];
                int newColor = color[current.from];
                for (int j = 1; j < n + 1; j++) {
                    if (color[j] == oldColor) {
                        color[j] = newColor;
                    }
                }
                remain--;
                if (remain == 1)
                    break;
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
