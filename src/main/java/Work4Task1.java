import java.io.*;
import java.util.*;


public class Work4Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String seq = in.next();
        if (seq.isEmpty()) {
            out.println("");
            return;
        }

        int[][] matrix = new int[seq.length()][seq.length()];
        int[][] minKMatrix = new int[seq.length()][seq.length()];

        for (int i = 0; i < seq.length(); i++) {
            matrix[i][i] = 1;
            minKMatrix[i][i] = -1;
        }

        for (int i = 1; i < seq.length(); i++) {
            for (int j = 0; j < seq.length() - i; j++) {
                int min = matrix[j][j] + matrix[j + 1][j + i];
                int minK = j;
                for (int k = j + 1; k < j + i; k++) {
                    int current = matrix[j][k] + matrix[k + 1][j + i];
                    if (min > current) {
                        min = current;
                        minK = k;
                    }
                }

                if (isPair(seq.charAt(j), seq.charAt(j + i)) && (matrix[j + 1][j + i - 1] < min)) {
                    matrix[j][j + i] = matrix[j + 1][j + i - 1];
                    minKMatrix[j][j + i] = -2;
                    continue;
                }

                minKMatrix[j][j + i] = minK;
                matrix[j][j + i] = min;
            }
        }
        int i = 0;
        int j = seq.length() - 1;

        if (matrix[i][j] == 0) {
            out.println(seq);
            return;
        }

        Queue<Integer> res = new LinkedList<>();
        findIndexes(minKMatrix, res, i, j);
        StringBuilder sb = new StringBuilder();

        Integer nextPut = res.poll();
        for (int k = 0; k < seq.length(); k++) {
            if (nextPut != null && k == nextPut) {
                switch (seq.charAt(k)) {
                    case '(': {
                        sb.append(seq.charAt(k)).append(')');
                        break;
                    }
                    case '[': {
                        sb.append(seq.charAt(k)).append(']');
                        break;
                    }
                    case ')': {
                        sb.append('(').append(seq.charAt(k));
                        break;
                    }
                    case ']': {
                        sb.append('[').append(seq.charAt(k));
                        break;
                    }
                }
                nextPut = res.poll();
            } else {
                sb.append(seq.charAt(k));
            }
        }
        out.println(sb);
    }

    private static void findIndexes(int[][] matrixForK, Queue<Integer> res, int i, int j) {
        int k = matrixForK[i][j];
        switch (k) {
            case -1:
                res.add(i);
                break;
            case -2:
                if (matrixForK[i + 1][j - 1] == 0) {
                    return;
                }
                findIndexes(matrixForK, res, i + 1, j - 1);
                break;
            default:
                findIndexes(matrixForK, res, i, k);
                findIndexes(matrixForK, res, k + 1, j);
                break;
        }
    }

    private static boolean isPair(char first, char second) {
        return (first == '(' && second == ')') || (first == '[' && second == ']');
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
                    String line = reader.readLine();
                    if (line == null) {
                        return "";
                    }
                    tokenizer = new StringTokenizer(line);
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
