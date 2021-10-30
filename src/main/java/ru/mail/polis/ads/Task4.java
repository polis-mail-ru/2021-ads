package ru.mail.polis.ads;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String template = sc.next();
        String str = sc.next();
        if (str.contains("?") || str.contains("*")) {
            String temp = template;
            template = str;
            str = temp;
        }
        boolean containsChar = false;
        boolean[][] d = new boolean[template.length()][str.length()];
        for (int i = 0; i < template.length(); i++) {
            if (template.charAt(i) != '*') {
                if (i == 0) {
                    d[0][0] = template.charAt(i) == '?' || template.charAt(0) == str.charAt(0);
                } else {
                    d[i][0] = (template.charAt(i) == '?' || template.charAt(i) == str.charAt(0)) && !containsChar;
                    for (int j = 1; j < str.length(); j++) {
                        d[i][j] = d[i - 1][j - 1] && (template.charAt(i) == '?' || template.charAt(i) == str.charAt(j));
                    }
                }
                containsChar = true;
            } else if (template.charAt(i) == '*') {
                if (i == 0) {
                    for (int j = 0; j < str.length(); j++) {
                        d[0][j] = true;
                    }
                } else {
                    d[i][0] = d[i - 1][0];
                    for (int j = 1; j < str.length(); j++) {
                        d[i][j] = d[i - 1][j] || d[i][j - 1];
                    }
                }
            }
        }
        System.out.println((d[template.length() - 1][str.length() - 1]) ? "YES" : "NO");
    }
}
