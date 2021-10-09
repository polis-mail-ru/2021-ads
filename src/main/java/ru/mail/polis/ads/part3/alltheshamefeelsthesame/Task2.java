package ru.mail.polis.ads.part3.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9481340

public class Task2 {

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

    private static class Heap {

        private final int[] arr;
        private int position = 1;

        public Heap(int N) {
            arr = new int[N];
        }

        private void swap(int idx1, int idx2) {
            int swap;
            swap = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = swap;
        }

        private void swim() {
            int pos = position - 1;

            while (pos > 1 && arr[pos] > arr[pos / 2]) {
                swap(pos, pos / 2);
                pos /= 2;
            }
        }

        private void sink() {
            int pos = 1;
            while (2 * pos < position) {
                int max = 2 * pos;
                if (max + 1 < position && arr[max + 1] > arr[max]) {
                    max++;
                }
                if (arr[pos] >= arr[max]) {
                    break;
                }

                swap(pos, max);
                pos = pos * 2 + max % 2;
            }
        }

        public void insert(int num) {
            arr[position++] = num;
            swim();
        }

        public void extract() {
            System.out.println(arr[1]);
            swap(1, --position);
            sink();
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        Heap heap = new Heap(N + 1);

        for (int i = 0; i < N; i++) {
            int operation = scanner.nextInt();
            if (operation == 0) {
                heap.insert(scanner.nextInt());
            } else {
                heap.extract();
            }
        }
    }
}
