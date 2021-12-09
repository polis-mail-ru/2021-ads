package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

//https://www.eolymp.com/ru/submissions/10074430
public class ShortestRoute {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Set<Integer>[] adjacencies = new Set[v + 1];

        for (int i = 1; i <= v; i++) {
            adjacencies[i] = new HashSet<>();
        }
        for (int i = 1; i <= e; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            adjacencies[from].add(to);
            adjacencies[to].add(from);
        }

        int[] d = new int[v + 1];
        int[] p = new int[v + 1];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(d, -1);
        Arrays.fill(p, -1);
        d[a] = 0;
        queue.add(a);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (int to : adjacencies[from]) {
                if (d[to] == -1) {
                    queue.add(to);
                    d[to] = d[from] + 1;
                    p[to] = from;
                }
            }
        }

        if (p[b] == -1) {
            out.println("-1");
            return;
        }
        out.println(d[b]);

        List<Integer> path = new ArrayList<>();
        path.add(b);
        int to = b;
        while (p[to] != -1) {
            to = p[to];
            path.add(to);
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            out.print(path.get(i) + " ");
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
