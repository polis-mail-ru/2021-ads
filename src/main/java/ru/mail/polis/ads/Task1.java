package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt(), e = in.nextInt(), a = in.nextInt() - 1, b = in.nextInt() - 1;

        List<Integer>[] list = new ArrayList[v];

        for (int i = 0; i < v; ++i) {
            list[i] = new ArrayList<Integer>();
        }

        int tempFrom, tempTo;

        for (int i = 0; i < e; ++i) {
            tempFrom = in.nextInt() - 1;
            tempTo = in.nextInt() - 1;
            list[tempFrom].add(tempTo);
            list[tempTo].add(tempFrom);
        }

        int[] dist = new int[v];
        Arrays.fill(dist, -1);
        dist[a] = 0;
        int[] parent = new int[v];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        while (!q.isEmpty()) {
            tempFrom = q.poll();
            for (int i = 0; i < list[tempFrom].size(); ++i) {
                tempTo = list[tempFrom].get(i);
                if (dist[tempTo] == -1) {
                    q.add(tempTo);
                    dist[tempTo] = dist[tempFrom] + 1;
                    parent[tempTo] = tempFrom;
                }
            }
        }

        if (parent[b] == -1) {
            out.println("-1");
            return;
        }

        out.println(dist[b]);

        List<Integer> path = new ArrayList<>();
        path.add(b + 1);
        tempTo = b;
        while (parent[tempTo] != -1) {
            tempTo = parent[tempTo];
            path.add(tempTo + 1);
        }
        for (int i = path.size() - 1; i >= 0; --i) {
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