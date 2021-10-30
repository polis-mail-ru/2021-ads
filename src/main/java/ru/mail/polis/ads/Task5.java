package ru.mail.polis.ads;

import java.util.Scanner;

public class Task5 {

    private static void printSequence(StringBuilder sb, boolean[] remains) {
        if (sb.length() == remains.length * 2 - 1) {
            System.out.println(sb);
            return;
        }
        for (int i = 1; i <= remains.length; i++) {
            if (!remains[i - 1]) { //available
                if (sb.length() != 0) {
                    sb.append(" ");
                }
                sb.append(i);
                remains[i - 1] = true;
                printSequence(sb, remains);
                remains[i - 1] = false;
                if (sb.length() > 1) {
                    sb.delete(sb.length() - 2, sb.length());
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        printSequence(sb, new boolean[n]);
    }
}