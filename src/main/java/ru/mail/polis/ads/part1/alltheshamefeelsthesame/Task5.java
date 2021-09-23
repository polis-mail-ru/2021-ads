package ru.mail.polis.ads.part1.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Task5 {

    private static class FastRead {
        private final BufferedReader reader;

        FastRead(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            String result = null;
            try {
                result = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

    }

    public static int reverseNotation(String expression) {
        Deque<Integer> operands = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (currentChar == ' ') {
                continue;
            }
            switch (currentChar) {
                case '*':
                    operands.addLast(operands.removeLast() * operands.removeLast());
                    break;
                case '+':
                    operands.addLast(operands.removeLast() + operands.removeLast());
                    break;
                case '-':
                    int first = operands.removeLast();
                    int second = operands.removeLast();
                    operands.addLast(second - first);
                    break;
                default:
                    operands.addLast(Character.getNumericValue(currentChar));
            }

        }

        return operands.getLast();
    }

    public static void main(String[] args) {
        FastRead reader = new FastRead(System.in);
        System.out.println(reverseNotation(reader.next()));
    }
}
