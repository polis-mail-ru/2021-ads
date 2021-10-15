package ru.mail.polis.ads.part4.anilochka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part4Task2 {
    private Part4Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        Pair[][] way = new Pair[m + 1][n + 1];
        //проинициализируем дополнительую верхнюю строку и дополнительный правый столбец -1 зернышком
        for (int i = 0; i <= m; i++) {
            way[i][n] = new Pair(-1, "");
        }
        for (int i = 0; i <= n; i++) {
            way[0][i] = new Pair(-1, "");
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n; j++) {
                way[i][j] = new Pair(in.nextInt(), "");
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = n - 1; j >= 0; j--) {
                int cost = Math.max(way[i - 1][j].getCost(), way[i][j + 1].getCost()) + way[i][j].getCost();
                if (cost == way[i][j].getCost() - 1) { // будет выполняться для первой строчки и последнего столбца искомого поля (без доп. полей)
                    way[i][j] = new Pair(way[i][j].getCost(), "");
                    continue;
                }
                String directions = way[i - 1][j].getCost() > way[i][j + 1].getCost() ? way[i - 1][j].getDirectionsSequence() + "F"
                        : way[i][j + 1].getDirectionsSequence() + "R";
                way[i][j] = new Pair(cost, directions);
            }
        }
        out.println(new StringBuilder().append(way[m][0].getDirectionsSequence()).reverse().toString());
    }

    private static class Pair {
        private final int cost;
        private final String directionsSequence;

        public Pair(int cost, String directionsSequence) {
            this.cost = cost;
            this.directionsSequence = directionsSequence;
        }

        public int getCost() {
            return cost;
        }

        public String getDirectionsSequence() {
            return directionsSequence;
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
