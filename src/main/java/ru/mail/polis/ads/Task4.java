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
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }


    private static void outMeta(List<? extends List<Integer>> map) {
        int numberOfEdges = 0;
        for (List<Integer> linkedList : map) {
            numberOfEdges += linkedList.size();
        }
        System.out.println(numberOfEdges);
    }

    private static class OrientedGraph {

        private final List<List<Integer>> graph;

        public OrientedGraph(List<List<Integer>> graph) {
            this.graph = graph;
        }

        /*
        Для исходного орграфа G = (V, E) построим
        его обращение (reverse of G) — орграф G^R = (V, E^R), где E^R = {(v, u) : (u, v) ∈ E}.
        Возвращает этот граф.
         */
        public static List<List<Integer>> getReverse(List<List<Integer>> inGraph) {
            List<List<Integer>> newGraph = new ArrayList<>();
            for (int i = 0; i < inGraph.size(); i++) {
                newGraph.add(new LinkedList<>());
            }
            for (int i = 0; i < inGraph.size(); i++) {
                for (int j : inGraph.get(i)) {
                    newGraph.get(j).add(i);
                }
            }
            return newGraph;
        }

        private final ArrayList<Boolean> visited = new ArrayList<>();
        private final LinkedList<Integer> order = new LinkedList<>();

        /*
        Обход G^R в глубину, построение списка всех вершин в порядке убывания их post-значений.
        Вход GR – орграф.
        Возвращает список list вершин по убыванию post значений
        Так как добавление в list происходит после обхода графа в глубину – в начале добавляются
        все элементы из конца обхода в глубину (самые “далекие” от текущей вершины), у них будет
        самое малое пост значение, а при поднятии – уровень пост значений будет возрастать, если
        деревьев несколько, то корень последнего по счету будет иметь наибольшее post значение.
        Время выполнения O(|V| + |E|), т.к. обход – DFS по GR проходят за линейное время
         O(|V| + |E|) + добавление в начало list – константное O(1)
         */
        public LinkedList<Integer> getSortedPost() {
            for (int i = 0; i < graph.size(); i++) {
                visited.add(false);
            }
            for (int i = 0; i < graph.size(); i++) {
                if (!visited.get(i)) {
                    visitGetPost(i);
                }
            }

            return order;
        }

        /*
         Рекурсивная ф-я того, что выше
         Принимает:
            u - текущая нода
         */
        private void visitGetPost(int u) {
            visited.set(u, true);
            for (int v : graph.get(u)) {
                if (!visited.get(v)) {
                    visitGetPost(v);
                }
            }
            order.addFirst(u);
        }

        public ArrayList<Integer> numSCC = new ArrayList<>();
        public int sizeOfSCC;

        /*
        Обход G = (V,E) в глубину и присваивание каждой вершине номер SCC.
        Принимает:
        Список вершин в порядке убывания их post-значений.
        Возвращает numSCC - массив, где ключ - номер вершины, а значение - номер SSC, которой принадлежит вершина
         */
        public List<Integer> getSCC(LinkedList<Integer> linkedList) {
            int curSCC = 0;
            for (int i = 0; i < graph.size(); i++) {
                visited.add(false);
                numSCC.add(-1);
            }
            for (int node : linkedList) {
                if (!visited.get(node)) {
                    visitGetSCC(node, curSCC);
                    curSCC++;
                }
            }
            sizeOfSCC = curSCC;
            return numSCC;
        }

        /*
        Рекурсивная ф-я того что выше
        Принимает:
        node - текущая нода
        curSCC - значение SCC, присваиваемое все элементам одной компоненты.
        */
        private void visitGetSCC(int node, int curSCC) {
            visited.set(node, true);

            for (int i : graph.get(node)) {
                if (!visited.get(i)) {
                    visitGetSCC(i, curSCC);
                }
            }
            numSCC.set(node, curSCC);
        }

        private final ArrayList<LinkedList<Integer>> metaGraphRev = new ArrayList<>();

        /*
            Функция, поочередно для всех SCC - обходит по всем элементам одной SCC и ищет соединения с другими SCC
            Возвращает metaGraph = (Vm, Em), где Vm – номер SSC, Em - список смежности этой SSC.
            Алгоритм BFS – Обходит абсолютно все вершины и ребра графа G.
            При обходе ребра {u,v} – мы проверяем принадлежность вершин u и v одной SSC, если они не принадлежат,
            то отмечаем это в metaGraph.
            BFS – работает за O(|V| + |E|), так же как и разворот,
            на при развороте {u,v} т.к. мы идем по массиву - все u - упорядочены, а значит, что все новые вершины
            добавляемые к v тоже будут упорядочены и нам надо рассмотреть лишь самый последний элемент для
            нахождения повторения
         */
        public List<LinkedList<Integer>> makeMetaGraph() {
            for (int i = 0; i < sizeOfSCC; i++) {
                metaGraphRev.add(new LinkedList<>());
            }

            for (int i = 0; i < graph.size(); i++) {
                visited.set(i, false);
            }
            for (int i = 0; i < graph.size(); i++) {
                if (!visited.get(i)) {
                    BFS(i);
                }
            }

            List<LinkedList<Integer>> metaGraph = new ArrayList<>();
            for (int i = 0 ; i < sizeOfSCC ; i++) {
                metaGraph.add(new LinkedList<>());
            }
            for (int i = 0; i < metaGraphRev.size(); i++) {
                for (int j : metaGraphRev.get(i)) {
                    if (metaGraph.get(j).isEmpty() || metaGraph.get(j).peekLast() != i) {
                        metaGraph.get(j).add(i);
                    }
                }
            }

            return metaGraph;
        }

        /*
        Для одной вершины проводит обход в ширину. Заполняет meta смежными вершинами.
        Вход сама вершина
         */
        private void BFS(int firstNode) {
            visited.set(firstNode, true);
            Deque<Integer> q = new LinkedList<>();
            q.push(firstNode);
            while (!q.isEmpty()) {
                int u = q.pop();
                int uSCC = numSCC.get(u);
                for (int v : graph.get(u)) {
                    int vSCC = numSCC.get(v);
                    if (uSCC != vSCC) {
                        metaGraphRev.get(uSCC).addFirst(vSCC);
                    }
                    if (!visited.get(v)) {
                        visited.set(v, true);
                        q.push(v);
                    }
                }
            }
        }
    }

    private static void solve(final FastScanner in) {
        int sizeV = in.nextInt();
        int sizeE = in.nextInt();
        List<List<Integer>> listOfAdjacency = new ArrayList<>(sizeV);
        for (int i = 0; i < sizeV; i++) {
            listOfAdjacency.add(new ArrayList<>());
        }
        int u, v;
        for (int i = 0; i < sizeE; i++) {
            u = in.nextInt() - 1;
            v = in.nextInt() - 1;
            listOfAdjacency.get(u).add(v);
        }


        OrientedGraph orientedGraph = new OrientedGraph(listOfAdjacency);
        OrientedGraph reversedOrientedGraph = new OrientedGraph(OrientedGraph.getReverse(listOfAdjacency));
        LinkedList<Integer> linkedList = reversedOrientedGraph.getSortedPost();

        List<Integer> numSCCOrderedByValue = orientedGraph.getSCC(linkedList);

        reversedOrientedGraph.numSCC = orientedGraph.numSCC;
        reversedOrientedGraph.sizeOfSCC = orientedGraph.sizeOfSCC;
        List<? extends List<Integer>> metaGraph = reversedOrientedGraph.makeMetaGraph();

        outMeta(metaGraph);
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
            solve(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}