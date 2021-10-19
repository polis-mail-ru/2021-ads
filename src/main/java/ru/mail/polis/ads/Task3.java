package ru.mail.polis.ads;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] first = new int[n];
        IntStream.range(0, n).forEach((i) -> first[i] = scanner.nextInt());
        int m = scanner.nextInt();
        int[] second = new int[m];
        IntStream.range(0, m).forEach((i) -> second[i] = scanner.nextInt());
        int[][] d = new int[n][m];
        int left;
        int top;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                left = (j > 0) ? d[i][j - 1] : 0;
                top = (i > 0) ? d[i - 1][j] : 0;
                d[i][j] = Math.max(left + ((second[j] == first[i]) ? 1 : 0), top);
            }
        }
        System.out.println(d[n - 1][m - 1]);
    }
}
