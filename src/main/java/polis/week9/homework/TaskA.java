package polis.week9.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class TaskA {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nodesCount = in.nextInt();
        int edgesCount = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();

        // Нумерация вершин начинается с 1. 0й элемент массива смежности использовать не будем
        List<Integer>[] adjacencyList = new LinkedList[nodesCount + 1];
        for (int i = 0; i < edgesCount; i++) {
            int nodeFrom = in.nextInt();
            int nodeTo = in.nextInt();

            if (adjacencyList[nodeFrom] == null) {
                adjacencyList[nodeFrom] = new LinkedList<>();
            }
            if (adjacencyList[nodeTo] == null) {
                adjacencyList[nodeTo] = new LinkedList<>();
            }

            adjacencyList[nodeFrom].add(nodeTo);
            adjacencyList[nodeTo].add(nodeFrom);
        }

        int[] destDyn = createDestDynamic(adjacencyList, start);
        Stack<Integer> way = getWay(adjacencyList, destDyn, start, end);

        if (way.isEmpty()) {
            out.println(-1);
            return;
        }

        out.println(destDyn[end]);
        while (!way.isEmpty()) {
            out.print(way.pop() + " ");
        }
    }

    private static int[] createDestDynamic(List<Integer>[] adjacencyList, int start) {
        int[] destDyn = new int[adjacencyList.length];
        Arrays.fill(destDyn, Integer.MAX_VALUE);
        destDyn[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            if (adjacencyList[curNode] != null) {
                for (int connNode : adjacencyList[curNode]) {
                    if (destDyn[connNode] == Integer.MAX_VALUE) {
                        destDyn[connNode] = destDyn[curNode] + 1;
                        queue.add(connNode);
                    }
                }
            }
        }

        return destDyn;
    }

    private static Stack<Integer> getWay(List<Integer>[] adjacencyList, int[] destDyn, int start, int end) {
        Stack<Integer> way = new Stack<>();
        if (destDyn[end] == Integer.MAX_VALUE) {
            return way;
        }

        way.push(end);
        while (way.peek() != start) {
            int bestNodeToGo = adjacencyList[way.peek()].get(0);
            for (int connNode : adjacencyList[way.peek()]) {
                if (destDyn[connNode] < destDyn[bestNodeToGo]) {
                    bestNodeToGo = connNode;
                }
            }
            way.push(bestNodeToGo);
        }

        return way;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

