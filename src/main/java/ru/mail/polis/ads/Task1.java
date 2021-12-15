package ru.mail.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */


// https://neerc.ifmo.ru/wiki/index.php?title=%D0%A1%D0%9D%D0%9C_(%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F_%D1%81_%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E_%D0%BB%D0%B5%D1%81%D0%B0_%D0%BA%D0%BE%D1%80%D0%BD%D0%B5%D0%B2%D1%8B%D1%85_%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D1%8C%D0%B5%D0%B2)
// DSU realisation
public final class Task1 {

    private static class Edge {
        final int u;
        final int v;
        final int weight;

        public int getWeight() {
            return weight;
        }

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    private Task1() {
        // Should not be instantiated
    }

    private static int[] p;
    private static int[] r;

    private static void init_DSU() {
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
            r[i] = 1;
        }
    }

    private static int get(int x) {
        int root = x;
        while (p[root] != root) {
            root = p[root];
        }
        int i = x;
        while (p[i] != i) {
            int j = p[i];
            p[i] = root;
            i = j;
        }
        return root;
    }

    private static boolean union(int x, int y) {
        x = get(x);
        y = get(y);
        if (x == y) {
            return false;
        }
        if (r[x] == r[y]) {
            r[x]++;
        }
        if (r[x] < r[y]) {
            p[x] = y;
        }
        else {
            p[y] = x;
        }
        return true;
    }

    private static void solve(final FastScanner in) {
        int sizeV = in.nextInt();
        int sizeE = in.nextInt();
        // parent array
        p = new int[sizeV];
        // rank array
        r = new int[sizeV];

        // Read list of edges
        List<Edge> edgeList = new ArrayList<>(sizeE);
        for (int i = 0; i < sizeE; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            Edge e = new Edge(u, v, weight);
            edgeList.add(e);
        }
        edgeList.sort(Comparator.comparingInt(Edge::getWeight));
        init_DSU();

        int ans = 0;
        for (Edge edge : edgeList) {
            if (union(edge.u, edge.v)) {
                ans += edge.weight;
            }
        }

        System.out.println(ans);
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
            solve(in);
        }
    }
}
