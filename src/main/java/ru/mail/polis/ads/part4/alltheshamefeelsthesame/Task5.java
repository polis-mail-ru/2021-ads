package ru.mail.polis.ads.part4.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task5 {

    private static int answer = 0;
    private static int[] newArray;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int[] array = new int[N];
        newArray = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        findCount(array, 0, array.length - 1);
        System.out.println(answer);
    }

    private static void findCount(int[] array, int i, int j) {
        if (i >= j) {
            return;
        }

        findCount(array, i, (i + j) / 2);
        findCount(array, (i + j) / 2 + 1, j);

        int currentI = i;
        int currentJ = (i + j) / 2  + 1;
        int newCur = 0;
        for (int k = 0; k <= (j - i) + 1; k++) {
            if (currentJ <= j && array[currentI] > array[currentJ]) {
                newArray[newCur++] = array[currentJ++];
                answer += ((i + j) / 2 - currentI) + 1;
            } else if (currentI <= (i + j) / 2) {
                newArray[newCur++] = array[currentI++];
            }
        }

        int idx = 0;
        for (int k = i; k < currentJ; k++) {
            array[k] = newArray[idx++];
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
}
