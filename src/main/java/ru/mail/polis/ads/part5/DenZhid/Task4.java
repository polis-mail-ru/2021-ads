package ru.mail.polis.ads.part5.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String inputWord = in.next();
        String inputPattern;
        if (inputWord.indexOf('*') == -1 & inputWord.indexOf('?') == -1) {
            inputPattern = in.next();
        } else {
            inputPattern = inputWord;
            inputWord = in.next();
        }
        if (inputWord.equals(inputPattern) || inputWord.equals("") && inputPattern.charAt(0) == '*') {
            out.println("YES");
            return;
        }
        int patternSize = inputPattern.length();
        int wordSize = inputWord.length();
        boolean[][] dynamicArray = new boolean[patternSize][wordSize]; // По умолчанию заполнен false
        char currentPatternChar = inputPattern.charAt(0);
        if (currentPatternChar == '*') {
            for (int j = 0; j < wordSize; j++) {
                dynamicArray[0][j] = true;
            }
        } else if (currentPatternChar == '?' || currentPatternChar == inputWord.charAt(0)) {
            dynamicArray[0][0] = true;
        } else {
            out.println("NO");
            return;
        } // Заполняем нулевую строку
        for (int i = 1; i < patternSize; i++) {
            currentPatternChar = inputPattern.charAt(i);
            if (currentPatternChar == '*') {
                dynamicArray[i][0] = dynamicArray[i - 1][0];
            } else { // Пока идут подряд звёздочки заполняем true, как только появляется - прерываем
                break;
            }
        } // Заполняем нулевой столбец
        for (int i = 1; i < patternSize; i++) {
            currentPatternChar = inputPattern.charAt(i);
            for (int j = 1; j < wordSize; j++) {
                if (currentPatternChar == '*') {
                    dynamicArray[i][j] = dynamicArray[i - 1][j] || dynamicArray[i][j - 1];
                } else if (currentPatternChar == '?' || currentPatternChar == inputWord.charAt(j)) {
                    dynamicArray[i][j] = dynamicArray[i - 1][j - 1];
                }
            }
        }
        out.println(dynamicArray[patternSize - 1][wordSize - 1]? "YES" : "NO");
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
