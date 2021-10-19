package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.isEmpty()) {
            return;
        }
        char[] brackets = input.toCharArray();
        int[][] d = new int[brackets.length][brackets.length];
        for (int i = 0; i < d.length; i++) {
            d[i][i] = 1;
        }
        int min;
        int sum;
        for (int j = 1; j < d.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                min = brackets.length + 1;
                if (brackets[j] == ')' && brackets[i] == '(' || brackets[j] == ']' && brackets[i] == '[') {
                    min = d[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    sum = d[i][k] + d[k + 1][j];
                    min = Math.min(sum, min);
                }
                d[i][j] = min;
            }
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        recoverAnswer(0, brackets.length - 1, d, indexes);
        indexes.sort(Integer::compare);
        int i = 0;
        for (int j = 0; j < brackets.length; j++) {
            if (i == indexes.size()) {
                for (int k = j; k < brackets.length; k++) {
                    System.out.print(brackets[k]);
                }
                break;
            }
            if (indexes.get(i) == j) {
                switch (brackets[j]) {
                    case ')':
                    case '(':
                        i++;
                        System.out.print("()");
                        break;
                    case ']':
                    case '[':
                        i++;
                        System.out.print("[]");
                        break;
                }
            } else {
                System.out.print(brackets[j]);
            }
        }
    }

    private static void recoverAnswer(int i, int j, int[][] d, ArrayList<Integer> indexes) {
        if (i == j) {
            indexes.add(i);
            return;
        }
        if (d[i][j] == 0) {
            return;
        }
        int sum;
        for (int k = i; k < j; k++) {
            sum = d[i][k] + d[k + 1][j];
            if (sum == d[i][j]) {
                recoverAnswer(i, k, d, indexes);
                recoverAnswer(k + 1, j, d, indexes);
                return;
            }
        }
        recoverAnswer(i + 1, j - 1, d, indexes);
    }
}