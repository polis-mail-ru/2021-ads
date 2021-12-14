package ru.mail.polis.ads.part10.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10124994
public final class Week10Test2 {
    private Week10Test2() {
        // Should not be instantiated
    }

    private static final int INF = Integer.MAX_VALUE;
    
    private static class Edge {

        private int edge;
        private int weight;

        public Edge(int edge, int weight) {
            this.edge = edge;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt();
        final int e = in.nextInt();

        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;

        List<Edge>[] graph = new ArrayList[v];
        boolean[] used = new boolean[v];
        int[] dist = new int[v + 1];
        int[] prev = new int[v + 1];

        dist[0] = INF;
        for (int i = 0; i < v; ++i) {
            graph[i] = new ArrayList<Edge>();
            dist[i + 1] = INF;
        }
        dist[a + 1] = 0;

        int temp1, temp2, temp3;
        for (int i = 0; i < e; ++i) {
            temp1 = in.nextInt() - 1;
            temp2 = in.nextInt() - 1;
            temp3 = in.nextInt();

            graph[temp1].add(new Edge(temp2, temp3));
            graph[temp2].add(new Edge(temp1, temp3));
        }

        PriorityQueue<Edge> q = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

        q.add(new Edge(a, 0));

        while (!q.isEmpty()) {
            temp1 = q.poll().edge;
            if (used[temp1]) {
                continue;
            }
            used[temp1] = true;
            ++temp1;
            for (Edge edge : graph[temp1 - 1]) {
                temp2 = edge.edge + 1;
                temp3 = dist[temp1] + edge.weight;
                if (dist[temp2] > temp3) {
                    dist[temp2] = temp3;
                    q.add(new Edge(temp2 - 1, dist[temp2]));
                    prev[temp2] = temp1;
                }
            }
        }

        Deque<Integer> res = new LinkedList<>();
        ++b;
        out.println(dist[b]);

        while (prev[b] != 0) {
            res.push(b);
            b = prev[b];
        }
        res.push(a + 1);
        while (!res.isEmpty()) {
            out.print(res.pop() + " ");
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
