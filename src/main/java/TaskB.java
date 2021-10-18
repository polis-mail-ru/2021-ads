import java.io.*;
import java.util.StringTokenizer;

public class TaskB {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeY = in.nextInt();
        int sizeX = in.nextInt();

        int[][] corns = new int[sizeY][sizeX];
        for (int i = sizeY - 1; i >= 0; i--) {
            for (int j = 0; j < sizeX; j++) {
                corns[i][j] = in.nextInt();
            }
        }

        int[][] maxEatten = new int[sizeY][sizeX];
        //Задаем базу
        maxEatten[0][0] = corns[0][0];
        //В левый столбец никак не попасть, кроме как снизу
        for (int i = 1; i < sizeY; i++) {
            maxEatten[i][0] = maxEatten[i - 1][0] + corns[i][0];
        }
        //В первую строку никак не попасть, кроме как слева
        for (int j = 1; j < sizeX; j++) {
            maxEatten[0][j] = maxEatten[0][j - 1] + corns[0][j];
        }

        //Проходим по всему полю
        for (int i = 1; i < sizeY; i++) {
            for (int j = 1; j < sizeX; j++) {
                maxEatten[i][j] = Math.max(maxEatten[i - 1][j], maxEatten[i][j - 1]) + corns[i][j];
            }
        }


        //Восстанавливаем путь
        StringBuilder way = new StringBuilder();
        restoreWay(maxEatten, sizeY - 1, sizeX - 1, way);

        out.println(way.reverse().toString());
    }

    private static void restoreWay(int[][] maxEatten, int i, int j, StringBuilder way) {
        if (i - 1 >= 0 && j - 1 >= 0) {
            if (maxEatten[i - 1][j] > maxEatten[i][j - 1]) {
                way.append("F");
                restoreWay(maxEatten, i - 1, j, way);
            } else {
                way.append("R");
                restoreWay(maxEatten, i, j - 1, way);
            }
        } else if (i - 1 >= 0) {
            way.append("F");
            restoreWay(maxEatten, i - 1, j, way);
        } else if (j - 1 >= 0) {
            way.append("R");
            restoreWay(maxEatten, i, j - 1, way);
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
