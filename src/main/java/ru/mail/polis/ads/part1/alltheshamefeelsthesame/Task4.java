package ru.mail.polis.ads.part1.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Task4 {

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

    public static boolean check(String str) {

        if (str.length() % 2 != 0) {
            return false;
        }

        Deque<Character> open = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                switch (currentChar) {
                    case '(':
                        open.addLast(')');
                        break;
                    case '[':
                        open.addLast(']');
                        break;
                    case '{':
                        open.addLast('}');
                }
            } else {
                Character last = open.pollLast();
                if (last == null || currentChar != last) {
                    return false;
                }
            }
        }
        return open.peekLast() == null;
    }

    public static void main(String[] args) {
        FastRead reader = new FastRead(System.in);
        String str = reader.next();

        if (check(str)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

}