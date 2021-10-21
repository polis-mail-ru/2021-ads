import java.io.*;
import java.util.StringTokenizer;

public class task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(); // количество строк
        int m = in.nextInt(); // количество столбцов
        int[][] floor = new int[n][m];

        StringBuilder ans = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) { // из нижней строки
            for (int j = 0; j < m; j++) { // первого столбца
                floor[i][j] = in.nextInt(); // заполняем матрицу (наоборот строки)
            }
        }
        for (int i = 1; i < n; i++) {
            floor[i][0] = floor[i][0] + floor[i - 1][0]; // проходим по первому столбцу
        }
        for (int j = 1; j < m; j++) {
            floor[0][j] = floor[0][j] + floor[0][j - 1]; // проходим по первой (последней) строке
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                floor[i][j] = floor[i][j] + Math.max(floor[i - 1][j], floor[i][j - 1]);
            }
        }
        int k = n - 1, t = m - 1;
        while (k > 0 || t > 0) {
            if (k > 0 && t > 0) {
                if (floor[k - 1][t] > floor[k][t - 1]) {
                    ans.append("F");
                    k--;
                } else {
                    ans.append("R");
                    t--;
                }
            } else if (k == 0) {
                ans.append("R");
                t--;
            } else if (t == 0) {
                ans.append("F");
                k--;
            }
        }
        String reverse = new StringBuffer(ans.toString()).reverse().toString();
        out.print(reverse);

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
