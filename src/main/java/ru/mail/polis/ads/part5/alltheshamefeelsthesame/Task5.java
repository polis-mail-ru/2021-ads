package ru.mail.polis.ads.part5.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task5 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        findAnswer(N);
    }

    private static void findAnswer(int N) {
        int[] array = IntStream.range(1, N + 1).toArray();
        int count = 1;
        for (int i = 1; i < N; i++) {
            count *= array[i];
        }

        int currentJ = 0;
        int currentL = 0;
        int rightBoard;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[j] + " ");

                if (j != 0 && array[j - 1] < array[j]) {
                    currentJ = j - 1;
                }
                if (j > currentJ && array[j] > array[currentJ]) {
                    currentL = j;
                }
            }

            swap(array, currentJ, currentL);

            rightBoard = (currentJ + array.length) % 2 == 0 ? (currentJ + array.length) / 2 - 1 : (currentJ + array.length) / 2;
            for (int j = currentJ + 1; j <= rightBoard; j++) {
                swap(array, j, array.length + currentJ - j);
            }
        }
    }

    private static void swap(int[] array, int j, int l) {
        int swap = array[j];
        array[j] = array[l];
        array[l] = swap;
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
