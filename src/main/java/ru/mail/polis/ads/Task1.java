package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeV = in.nextInt();
        int sizeE = in.nextInt();
        int startNode = in.nextInt() - 1;
        int endNode = in.nextInt() - 1;
        List<List<Integer>> listOfAdjacency = new ArrayList<>(sizeV);
        for (int i = 0; i < sizeV; i++) {
            listOfAdjacency.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < sizeE; i++) {
            u = in.nextInt() - 1;
            v = in.nextInt() - 1;
            listOfAdjacency.get(u).add(v);
            listOfAdjacency.get(v).add(u);
        }

        BFS(startNode, endNode, listOfAdjacency);
    }

    public static void BFS(int start, int end, List<List<Integer>> listOfAdjacency) {
        Deque<Integer> q = new LinkedList<>();
        List<Integer> d = new ArrayList<>(listOfAdjacency.size());
        List<Integer> p = new ArrayList<>(listOfAdjacency.size());
        for (int i = 0; i < listOfAdjacency.size(); i++) {
            d.add(-1);
            p.add(-1);
        }

        p.set(start, start);
        d.set(start, 0);
        q.push(start);
        while (!q.isEmpty()) {
            int u = q.pop();
            for (int v : listOfAdjacency.get(u)) {
                if (d.get(v) == -1) {
                    p.set(v, u);
                    d.set(v, d.get(u) + 1);
                    if (v == end) {
                        q.clear();
                        break;
                    }
                    q.add(v);
                }
            }
        }
        print(start, end, d, p);
    }

    private static void print(int start, int end, List<Integer> d, List<Integer> p) {
        int[] result = new int[1 + d.get(end)];
        for (int i = result.length - 1, current = end; i >= 0; i--, current = p.get(current)) {
            result[i] = current;
        }
        System.out.println(result.length - 1);
        if (result.length == 0) {
            return;
        }
        for (int i : result) {
            System.out.print((i + 1) + " ");
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
