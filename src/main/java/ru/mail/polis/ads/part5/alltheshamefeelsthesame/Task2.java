package ru.mail.polis.ads.part5.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {

    private static long width;
    private static long height;
    private static long count;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        width = scanner.nextInt();
        height = scanner.nextInt();
        count = scanner.nextInt();

        System.out.println(binSearch());
    }

    private static long binSearch() {
        long left = Math.max(width, height);
        long right = Math.max(width, height) * count;
        long mid;

        while (left < right) {
            mid = (left + right) / 2;
            if ((mid / width) * (mid / height) >= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
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
