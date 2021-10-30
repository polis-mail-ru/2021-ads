package ru.mail.polis.ads;

import java.util.Scanner;

public class Task2 {
    private static long leftBinarySearch(int w, int h, int n) {
        long l = Math.max(w, h);
        long r = l * n;
        long mid;
        while (l < r) {
            mid = (l + r) / 2;
            if ((mid / h) * (mid / w) >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(leftBinarySearch(sc.nextInt(), sc.nextInt(), sc.nextInt()));

    }
}
