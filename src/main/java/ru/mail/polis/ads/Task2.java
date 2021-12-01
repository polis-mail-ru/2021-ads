package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph(sc.nextInt());
        int m = sc.nextInt();
        IntStream.range(0, m).forEach(i -> graph.addEdge(sc.nextInt() - 1, sc.nextInt() - 1));
        graph.topologySort();
    }

    static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final int m;

        public Graph(int m) {
            this.m = m;
            this.adjacencyList = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int first, int second) {
            adjacencyList.get(first).add(second);
        }

        public void topologySort() {
            Stack<Integer> stack = new Stack<>();
            BitSet visited = new BitSet(m);
            BitSet blacks = new BitSet(m);
            for (int i = 0; i < m; i++) {
                if (!visited.get(i)) {
                    if (!dfs(visited, blacks, stack, i)) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
            IntStream.range(0, m - 1).forEach((i) -> System.out.printf("%d ", stack.pop()));
            System.out.print(stack.pop());
        }

        private boolean dfs(BitSet visited, BitSet blacks, Stack<Integer> stack, int node) {
            visited.set(node);
            for (int edge : adjacencyList.get(node)) {
                if (visited.get(edge) && !blacks.get(edge)) {
                    return false;
                }
                if (!visited.get(edge) && !dfs(visited, blacks, stack, edge)) {
                    return false;
                }
            }
            stack.push(node + 1);
            blacks.set(node);
            return true;
        }
    }
}
