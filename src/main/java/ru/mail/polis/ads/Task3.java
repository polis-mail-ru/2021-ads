package ru.mail.polis.ads;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        int[] d = new int[n];
        IntStream.range(0, n).forEach((i) -> array[i] = sc.nextInt());
        for (int i = 0; i < n; i++) {
            d[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] != 0 && array[i] % array[j] == 0) {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
        }
        System.out.println(Arrays.stream(d).max().getAsInt());
    }
}
