package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph(sc.nextInt());
        int m = sc.nextInt();
        IntStream.range(0, m).forEach(i -> graph.addEdge(sc.nextInt() - 1, sc.nextInt() - 1));
        graph.condGraph();
    }

    static class Graph {
        private ArrayList<ArrayList<Integer>> adjacencyList;
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

        private void reverseGraph() {
            ArrayList<ArrayList<Integer>> newAdjacencyList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                newAdjacencyList.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                for (int edge : adjacencyList.get(i)) {
                    newAdjacencyList.get(edge).add(i);
                }
            }
            adjacencyList = newAdjacencyList;
        }

        public Integer[] topologySort(Stack<Integer> stack, BitSet visited) {
            for (int i = 0; i < m; i++) {
                if (!visited.get(i)) {
                    dfs(visited, stack, i);
                }
            }
            return stack.toArray(new Integer[0]);
        }

        private void dfs(BitSet visited, Stack<Integer> stack, int node) {
            visited.set(node);
            for (int edge : adjacencyList.get(node)) {
                if (!visited.get(edge)) {
                    dfs(visited, stack, edge);
                }
            }
            stack.push(node);
        }

        private void dfs2(int c, int[] colors, int node) {
            colors[node] = c;
            for (int edge : adjacencyList.get(node)) {
                if (colors[edge] == -1) {
                    dfs2(c, colors, edge);
                }
            }
        }

        public void condGraph() {
            Stack<Integer> stack = new Stack<>();
            BitSet visited = new BitSet(m);
            Integer[] topologySorted = topologySort(stack, visited);//[3, 4, 5, 2, 1, 0]
            visited.clear();
            int[] colors = new int[m];
            Arrays.fill(colors, -1);
            int c = 0;
            reverseGraph();
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (colors[topologySorted[i]] == -1) {
                    dfs2(c++, colors, topologySorted[i]);
                }
            }
            Map<Integer, Set<Integer>> hm = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int edge : adjacencyList.get(i)) {
                    if (colors[i] != colors[edge]) {
                        if (!hm.containsKey(colors[i])) {
                            Set<Integer> set = new HashSet<>();
                            set.add(colors[edge]);
                            hm.put(colors[i], set);
                        } else {
                            hm.get(colors[i]).add(colors[edge]);
                        }
                    }
                }
            }
            System.out.println(hm.values().stream().mapToInt(Set::size).sum());
        }
    }
}