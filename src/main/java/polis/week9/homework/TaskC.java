package polis.week9.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nodesCount = in.nextInt();
        int edgesCount = in.nextInt();

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

        int minNode = findMinInCycles(adjacencyList);
        if (minNode != Integer.MAX_VALUE) {
            out.println("Yes");
            out.println(minNode);
        } else {
            out.println("No");
        }

    }

    private static int findMinInCycles(List<Integer>[] adjacencyList) {
        LinkedList<Integer> recStack = new LinkedList<>();
        Color[] colors = new Color[adjacencyList.length];
        Arrays.fill(colors, Color.WHITE);
        boolean[] isCycle = new boolean[adjacencyList.length];
        Arrays.fill(isCycle, false);
        int[] previousNodes = new int[adjacencyList.length];

        for (int node = 1; node < adjacencyList.length; node++) {
            if (colors[node].equals(Color.WHITE)) {
                recStack.clear();
                recStack.add(node);
                dfs(adjacencyList, recStack, colors, isCycle, previousNodes);
            }
        }

        int minNodeInCycle = Integer.MAX_VALUE;
        for (int node = 1; node < adjacencyList.length; node++) {
            if (isCycle[node] && node < minNodeInCycle) {
                minNodeInCycle = node;
            }
        }

        return minNodeInCycle;
    }

    private static void dfs(List<Integer>[] adjacencyList, Deque<Integer> recStack, Color[] colors, boolean[] isCycle, int[] previousNodes) {
        while (!recStack.isEmpty()) {
            int node = recStack.peek();
            colors[node] = Color.GRAY;
            boolean isColored = true;

            if (adjacencyList[node] != null) {
                for (int connNode : adjacencyList[node]) {
                    if (previousNodes[node] == connNode) {
                        continue;
                    }
                    if (colors[connNode] == Color.WHITE) {
                        previousNodes[connNode] = node;
                        recStack.push(connNode);
                        isColored = false;
                        break;
                    } else if (colors[connNode] == Color.GRAY) {
                        isCycle[connNode] = true;

                        int currNode = node;
                        while (currNode != connNode && !isCycle[currNode]) {
                            isCycle[currNode] = true;
                            currNode = previousNodes[currNode];
                        }
                    }
                }
            }
            if (isColored) {
                recStack.pop();
                colors[node] = Color.BLACK;
            }
        }
    }

    private enum Color {
        GRAY,
        BLACK,
        WHITE
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

