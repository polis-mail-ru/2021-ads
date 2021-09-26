package ru.mail.polis.ads.part1.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {

    private static class Node {
        int data;
        Node nextPointer;

        Node(int data, Node nextPointer) {
            this.data = data;
            this.nextPointer = nextPointer;
        }

    }

    public static class Queue {

        private Node head = null;
        private Node lastElement = null;
        private int quantity = 0;

        public int front() {
            if (head == null) {
                throw new NullPointerException("Empty queue");
            }
            return head.data;
        }

        public int size() {
            return quantity;
        }

        public void clear() {
            head = null;
            lastElement = null;
            quantity = 0;
            System.out.println("ok");
        }

        public void push(int num) {
            Node newElement = new Node(num, null);
            if (head != null) {
                lastElement.nextPointer = newElement;
            } else {
                head = newElement;
            }
            lastElement = newElement;
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

    }

    public static void main(String[] args) throws IOException {

        Queue queue = new Queue();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();

        while (!str.equals("exit")) {

            if (str.contains("push")) {
                queue.push(Integer.parseInt(str.substring(5)));
                System.out.println("ok");
            } else {
                int result;
                switch (str) {
                    case "pop":
                        try {
                            System.out.println(queue.pop());
                        } catch (NullPointerException e) {
                            System.out.println("error");
                        }
                        break;
                    case "front":
                        try {
                            System.out.println(queue.front());
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
