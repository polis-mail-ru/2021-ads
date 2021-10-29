package ru.mail.polis.ads.part5.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {

    private static String sample;
    private static String original;
    private static final int POSSIBLE = 1;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        original = scanner.next();
        sample = scanner.next();
        changeStrings();

        System.out.println(check());
    }

    private static void changeStrings() {
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == '?' || original.charAt(i) == '*') {
                String swap = original;
                original = sample;
                sample = swap;
                break;
            }
        }

        StringBuilder newSample = new StringBuilder();
        newSample.append(sample.charAt(0));
        int strIdx = 0;
        for (int i = 1; i < sample.length(); i++) {
            if ((newSample.charAt(strIdx) == '?' || newSample.charAt(strIdx) == '*')
            && (sample.charAt(i) == '?' || sample.charAt(i) == '*')) {
                newSample.setCharAt(strIdx, '*');
                continue;
            }
            newSample.append(sample.charAt(i));
            strIdx++;
        }

        sample = newSample.toString();
        System.out.println(sample);
    }

    private static String check() {
        int[][] dynamics = new int[sample.length()][original.length()];
        int stop;
        for (int i = 0; i < sample.length(); i++) {
            if (sample.charAt(i) == '*' && i == sample.length() - 1) {
                return "YES";
            }

            if (i == 0) {
                if (sample.charAt(0) == '*') {
                    dynamics[0][0] = POSSIBLE;
                    continue;
                }

                if (sample.charAt(0) == '?' || sample.charAt(0) == original.charAt(0)) {
                    dynamics[0][0] = POSSIBLE;
                    continue;
                }
                return "NO";
            }

            stop = 0;
            for (int j = 1; j < original.length(); j++) {
                if (sample.charAt(i) != '*') {
                    if (dynamics[i - 1][j - 1] == POSSIBLE &&
                            (sample.charAt(i) == '?' || sample.charAt(i) == original.charAt(j))) {
                        dynamics[i][j] = POSSIBLE;
                        stop++;
                    }
                } else {
                    if (j != original.length() - 1) {
                        if (original.charAt(j + 1) == sample.charAt(i + 1)) {
                            dynamics[i][j] = POSSIBLE;
                            stop++;
                        }
                    }
                }
            }

            if (stop == 0) {
                return "NO";
            }
        }
        return dynamics[sample.length() - 1][original.length() - 1] == POSSIBLE ? "YES" : "NO";
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
    }
}
