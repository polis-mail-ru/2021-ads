package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {

    public static double EPS = 1E-6;

    private static double function(double x, double c) {
        return x * x + Math.sqrt(x) - c;
    }

    private static void a() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            double c = Double.parseDouble(br.readLine());
            double right = c;
            double left = 1.0;
            double middle;
            while (right - left > EPS) {
                middle = (right + left) / 2;
                if (function(middle, c) * function(right, c) <= 0) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
            System.out.println(right);
        } catch (IOException e) {
            System.out.println("IOE exception");
        }
    }

    public static void main(String[] args) {
        a();
    }
}