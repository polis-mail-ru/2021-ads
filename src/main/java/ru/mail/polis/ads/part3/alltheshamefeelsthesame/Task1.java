package ru.mail.polis.ads.part3.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9477500

public class Task1 {

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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int N = scanner.nextInt();
        int[] arr = new int[N + 1];
        arr[1] = scanner.nextInt();
        int flag = 0;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            if (arr[i] < arr[i / 2]) {
                flag++;
                System.out.println("NO");
                break;
            }
        }

        if (flag == 0) {
            System.out.println("YES");
        }
    }

}
