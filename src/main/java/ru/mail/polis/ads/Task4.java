package ru.mail.polis.ads;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] sequence = new int[n + 1];
        IntStream.range(0, n).forEach((i) -> sequence[i] = scanner.nextInt());
        int[] d = new int[n + 2];
        int k = scanner.nextInt();
        int max;
        for (int i = 1; i < d.length; i++) {
            max = -10000001;
            for (int j = i - 1; j >= Math.max(i - k, 0); j--) {
                if (d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max + sequence[i - 1];
        }
        System.out.println(d[n + 1]);
    }
}
