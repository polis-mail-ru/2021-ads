package ru.mail.polis.ads;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Task5 {
    static int inversionCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        IntStream.range(0, n).forEach((i) -> arr[i] = scanner.nextInt());
        mergeSort(arr);
        System.out.println(inversionCount);
    }

    private static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int l, int r) {
        if (l == r) {
            return;
        }
        int middle = (l + r) / 2;
        mergeSort(arr, temp, l, middle);
        mergeSort(arr, temp, middle + 1, r);
        merge(arr, temp, l, middle, r);
    }

    private static void merge(int[] arr, int[] temp, int l, int middle, int r) {
        int i = l;
        int j = middle + 1;
        for (int k = l; k <= r; k++) {
            int result = arr[j] - arr[i];
            if (result > 0) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
                inversionCount += middle + 1 - i;
            }
            if (i == middle + 1) {
                for (int m = k + 1; m <= r; m++) {
                    temp[m] = arr[j];
                    j++;
                }
                break;
            }
            if (j == r + 1) {
                for (int m = k + 1; m <= r; m++) {
                    temp[m] = arr[i];
                    i++;
                }
                break;
            }
        }
        for (int k = l; k <= r; k++) {
            arr[k] = temp[k];
        }
    }
}
