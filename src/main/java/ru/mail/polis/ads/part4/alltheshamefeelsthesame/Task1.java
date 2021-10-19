package ru.mail.polis.ads.part4.alltheshamefeelsthesame;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1 {

    private static final int MIN = 101;
    private static int[][] table;
    private static int[][] helpTable;
    private static String sequence;

    private static int findNum(int i, int j) {
        char charI = sequence.charAt(i);
        char charJ = sequence.charAt(j);
        int check = -1;
        if ((charI == '(' && charJ == ')') || (charI == '[' && charJ == ']')) {
            check = table[i + 1][j - 1];
        }

        int min = MIN;
        int current;
        for (int k = i; k < j; k++) {
            current = table[i][k] + table[k + 1][j];
            if (current < min) {
                helpTable[i][j] = k;
                table[i][j] = k;
                min = current;
            }
        }
        if (check != -1 && check < min) {
            helpTable[i][j] = -1;
            return check;
        }
        return min;
    }

    private static void result(int start, int end, PrintWriter out) {
       if (start > end) {
            return;
        }

        if (start == end) {
            if (sequence.charAt(start) == '(' || sequence.charAt(start) == ')') {
                out.print("()");
            } else {
                out.print("[]");
            }
            return;
        }

        int k;
        if (helpTable[start][end] == -1) {
            out.print(sequence.charAt(start));
            result(start + 1, end - 1, out);
            out.print(sequence.charAt(end));
        } else {
            k = helpTable[start][end];
            result(start, k, out);
            result(k + 1, end, out);
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        if (!in.hasNext()) {
            return;
        }

        sequence = in.next();
        int N = sequence.length();
        table = new int[N][N];
        helpTable = new int[N][N];

        for (int i = 0; i < N; i++) {
            table[i][i] = 1;
        }

        int i;
        int j;
        for (int parts = 1; parts < N; parts++) {
            i = 0;
            j = parts;
            while (j < N) {
                table[i][j] = findNum(i++, j++);
            }
        }
        result(0, N - 1, out);
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            Scanner in = new Scanner(System.in);
            solve(in, out);
        }
    }
}