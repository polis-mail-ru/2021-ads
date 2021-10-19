package ru.mail.polis.ads;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] array = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        int[][] d = new int[m][n];
        int left;
        int bottom;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                left = (j > 0) ? d[i][j - 1] : 0;
                bottom = (i > 0) ? d[i - 1][j] : 0;
                d[i][j] = Math.max(left, bottom) + array[i][j];
            }
        }
        int i = m - 1;
        int j = n - 1;
        StringBuilder result = new StringBuilder();
        while (i != 0 || j != 0) {
            left = (j > 0) ? d[i][j - 1] : -1;
            bottom = (i > 0) ? d[i - 1][j] : -1;
            if (left >= bottom) {
                result.append("R");
                j--;
            } else {
                result.append("F");
                i--;
            }
        }
        System.out.println(result.reverse());
    }
}
