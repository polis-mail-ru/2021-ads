package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Graph graph = new Graph(in.nextInt());
        int m = in.nextInt();
        IntStream.range(0, m).forEach(i -> graph.addEdge(in.nextInt() - 1, in.nextInt() - 1));
        graph.findMinInCycle();
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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

    static class Graph {

        private final ArrayList<ArrayList<Integer>> listOfNeighbours;
        private final int n;
        private int min = Integer.MAX_VALUE;
        BitSet cycledNodes = new BitSet();

        public Graph(int n) {
            this.n = n;
            this.listOfNeighbours = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                listOfNeighbours.add(new ArrayList<>());
            }
        }

        public void addEdge(int first, int second) {
            listOfNeighbours.get(first).add(second);
            listOfNeighbours.get(second).add(first);
        }

        public void findMinInCycle() {
            BitSet visited = new BitSet(n);
            BitSet blacks = new BitSet(n);
            BitSet inCycle = new BitSet(n);
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < n; i++) {
                if (!visited.get(i)) {
                    depthSearch(visited, blacks, inCycle, i, -1);
                }
            }
            if (min == Integer.MAX_VALUE) {
                System.out.println("No");
                return;
            }
            System.out.printf("Yes\n%d", min + 1);
        }


        private void depthSearch(BitSet visited, BitSet dark, BitSet inCycle, int node, int prev) {
            visited.set(node);
            for (int edge : listOfNeighbours.get(node)) {
                if (visited.get(edge) && (!dark.get(edge)) && (edge != prev)) {
                    inCycle.set(edge);
                    inCycle.set(node);
                    cycledNodes.set(edge);
                }
                if (!visited.get(edge)) {
                    depthSearch(visited, dark, inCycle, edge, node);
                    if (inCycle.get(edge) && !cycledNodes.get(edge)) {
                        inCycle.set(node);
                    }
                }
            }
            dark.set(node);
            if (inCycle.get(node)) {
                min = Math.min(min, node);
            }
        }
    }
}