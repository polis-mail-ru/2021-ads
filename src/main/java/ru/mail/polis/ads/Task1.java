package ru.mail.polis.ads;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heap = new int[n];
        IntStream.range(0, n).forEach((i) -> heap[i] = scanner.nextInt());
        System.out.println((isHeap(heap) ? "YES" : "NO"));
    }

    private static boolean isHeap(int[] heapCandidate) {
        return depthSearch(1, 0, heapCandidate);
    }

    private static boolean depthSearch(int start, int previous, int[] array) {
        boolean valid1;
        boolean valid2;
        if (start * 2 <= array.length) {
            valid1 = depthSearch(start * 2, start, array);
        } else {
            valid1 = previous == 0 || array[previous - 1] <= array[start - 1];
        }
        if (!valid1) {
            return false;
        }
        if (start * 2 + 1 <= array.length) {
            valid2 = depthSearch(start * 2 + 1, start, array);
        } else {
            valid2 = previous == 0 || array[previous - 1] <= array[start - 1];
        }
        return valid2;
    }

}
