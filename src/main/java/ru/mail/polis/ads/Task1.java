package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph(sc.nextInt());
        int m = sc.nextInt();
        int start = sc.nextInt();
        int finish = sc.nextInt();
        IntStream.range(0, m).forEach(i -> graph.addEdge(sc.nextInt() - 1, sc.nextInt() - 1));
        graph.findShortestPath(start - 1, finish - 1);
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
            adjacencyList.get(second).add(first);
        }

        public void findShortestPath(int start, int finish) {
            BitSet visited = new BitSet(m);
            HashMap<Integer, Integer> prev = new HashMap<>();
            bfs(visited, prev, start, finish);
            prev.put(start, -1);
            if (!visited.get(finish)) {
                System.out.println(-1);
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i = finish;
            int count = 0;
            while (true) {
                sb.append(i + 1);
                count++;
                i = prev.get(i);
                if (i == -1) {
                    break;
                }
                sb.append(" ");
            }
            System.out.println(count - 1);
            System.out.println(sb.reverse());
        }

        public void bfs(BitSet visited, HashMap<Integer, Integer> prev, int start, int finish) {
            MyQueue<Integer> queue = new MyQueue<>();
            queue.push(start);
            visited.set(start);
            while (!queue.isEmpty()) {
                int node = queue.pop();
                if (node == finish) {
                    return;
                }
                for (int edge : adjacencyList.get(node)) {
                    if (!visited.get(edge)) {
                        visited.set(edge);
                        prev.put(edge, node);
                        queue.push(edge);
                    }
                }

            }
        }
    }


}
class MyQueue<T> {
    private final LinkedList<T> queue;

    public MyQueue() {
        this.queue = new LinkedList<>();
    }

    public void push(T el) {
        queue.addLast(el);
    }

    public T pop() {
        return (!queue.isEmpty()) ? queue.removeFirst() : null;
    }

    public T front() {
        return (!queue.isEmpty()) ? queue.getFirst() : null;
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

}