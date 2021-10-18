package ru.mail.polis.ads.part4.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9576792
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.nextLine();
        BracketSequence bracketSequence = new BracketSequence(str);
        bracketSequence.modifySequenceToValid();
        out.println(bracketSequence.getSequence());
    }

    private static class BracketSequence {
        private static final int VISITED_POSITION = Integer.MAX_VALUE;

        private final String sequence;
        private final int sequenceSize;
        private final int[][] memoizationMatrix; // матрица для мемоизации
        private final int[][] alterationMatrix; // матрица для восстановления ответа
        private StringBuilder validSequence;

        public BracketSequence(String sequence) {
            this.sequence = sequence;
            sequenceSize = sequence.length();
            memoizationMatrix = new int[sequenceSize][sequenceSize];
            alterationMatrix = new int[sequenceSize][sequenceSize];
            for (int i = 0; i < sequenceSize; i++) {
                for (int j = 0; j < sequenceSize; j++) {
                    alterationMatrix[i][j] = memoizationMatrix[i][j] = VISITED_POSITION;
                }
            }
        }

        public void modifySequenceToValid() {
            buildTable(0, sequenceSize - 1);
        }

        public String getSequence() {
            validSequence = new StringBuilder();
            print(0, sequenceSize - 1);
            return validSequence.toString();
        }

        private int buildTable(int i, int j) {
            if (i > j) {
                return 0;
            }
            if (memoizationMatrix[i][j] != VISITED_POSITION) {
                return memoizationMatrix[i][j];
            }
            if (i == j) {
                return 1;
            }
            if ((sequence.charAt(i) == '(' && sequence.charAt(j) == ')')
                    || (sequence.charAt(i) == '[' && sequence.charAt(j) == ']')) {
                memoizationMatrix[i][j] = Math.min(memoizationMatrix[i][j], buildTable(i + 1, j - 1));
            }
            for (int k = i; k < j; k++) {
                final int tmp = buildTable(i, k) + buildTable(k + 1, j);
                if (tmp < memoizationMatrix[i][j]) {
                    alterationMatrix[i][j] = k;
                    memoizationMatrix[i][j] = tmp;
                }
            }
            return memoizationMatrix[i][j];
        }

        private void print(int i, int j) {
            if (i > j) return;
            if (i == j) {
                if (sequence.charAt(i) == '(' || sequence.charAt(i) == ')') {
                    validSequence.append("()");
                } else {
                    validSequence.append("[]");
                }
            } else if (alterationMatrix[i][j] == VISITED_POSITION) {
                validSequence.append(sequence.charAt(i));
                print(i + 1, j - 1);
                validSequence.append(sequence.charAt(j));
            } else {
                print(i, alterationMatrix[i][j]);
                print(alterationMatrix[i][j] + 1, j);
            }
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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