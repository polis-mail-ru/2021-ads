package ru.mail.polis.ads.part1.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {

    private static class Node {

        int data;
        Node nextPointer;

        Node(int data, Node nextPointer) {
            this.data = data;
            this.nextPointer = nextPointer;
        }

    }

    public static class Stack {

        Node head = null;
        int quantity = 0;

        public void push(int num) {
            head = new Node(num, head);
            quantity++;
        }

        public int pop() {
            if (head == null) {
                throw new NullPointerException("Empty queue");
            }
            int result = head.data;
            head = head.nextPointer;
            quantity--;
            return result;
        }

        public void clear() {
            head = null;
            System.out.println("ok");
            quantity = 0;
        }

        public int size() {
            return quantity;
        }

        public int back() {
            if (head == null) {
                throw new NullPointerException("Empty queue");
            }
            return head.data;
        }


    }

    public static void main(String[] args) throws IOException {

        Stack queue = new Stack();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();

        while (!str.equals("exit")) {

            if (str.contains("push")) {
                queue.push(Integer.parseInt(str.substring(5)));
                System.out.println("ok");
            } else {
                switch (str) {
                    case "pop":
                        try {
                            System.out.println(queue.pop());
                        } catch (NullPointerException e) {
                            System.out.println("error");
                        }
                        break;
                    case "back":
                        try {
                            System.out.println(queue.back());
                        } catch (NullPointerException e) {
                            System.out.println("error");
                        }
                        break;
                    case "size":
                        System.out.println(queue.size());
                        break;
                    case "clear":
                        queue.clear();
                }
            }
            str = br.readLine();
        }
        System.out.println("bye");
    }

}
