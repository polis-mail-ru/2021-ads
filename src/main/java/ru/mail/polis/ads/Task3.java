package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph(sc.nextInt());
        int m = sc.nextInt();
        IntStream.range(0, m).forEach(i -> graph.addEdge(sc.nextInt() - 1, sc.nextInt() - 1));
        graph.findMinInCycle();
    }

    static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final int m;
        private int min = Integer.MAX_VALUE;
        BitSet cycledNodes = new BitSet();

        public Graph(int m) {
            this.m = m;
            this.adjacencyList = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int first, int second) {
            adjacencyList.get(first).add(second);
            adjacencyList.get(second).add(first);
        }

        public void findMinInCycle() {
            BitSet visited = new BitSet(m);
            BitSet blacks = new BitSet(m);
            BitSet inCycle = new BitSet(m);
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < m; i++) {
                if (!visited.get(i)) {
                    dfs(visited, blacks, inCycle, i, -1);
                }
            }
            if (min == Integer.MAX_VALUE) {
                System.out.println("No");
                return;
            }
            System.out.printf("Yes\n%d", min + 1);
        }


        private void dfs(BitSet visited, BitSet blacks, BitSet inCycle, int node, int previous) {
            visited.set(node);
            for (int edge : adjacencyList.get(node)) {
                if (visited.get(edge) && !blacks.get(edge) && edge != previous) {
                    inCycle.set(edge);
                    inCycle.set(node);
                    cycledNodes.set(edge);
                }
                if (!visited.get(edge)) {
                    dfs(visited, blacks, inCycle, edge, node);
                    if (inCycle.get(edge) && !cycledNodes.get(edge)) {
                        inCycle.set(node);
                    }
                }
            }
            blacks.set(node);
            if (inCycle.get(node)) {
                min = Math.min(min, node);
            }
        }
    }
}