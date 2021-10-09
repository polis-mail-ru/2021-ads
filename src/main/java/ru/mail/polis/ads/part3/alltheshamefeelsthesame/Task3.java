package ru.mail.polis.ads.part3.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//https://www.e-olymp.com/ru/submissions/9486766

public class Task3 {

    private static class FastScanner {
        private final BufferedReader reader;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }
    }

    private static class Heap {
        private final ArrayList<Integer> heap = new ArrayList<>();
        private final Comparator<Integer> comparator;

        public Heap(Comparator<Integer> comparator) {
            this.comparator = comparator;
            heap.add(0, null);
        }

        private void swim() {
            int position = size() - 1;
            while (position != 1) {
                if (comparator.compare(heap.get(position), heap.get(position / 2)) < 0) {
                    break;
                }
                Collections.swap(heap, position, position / 2);
                position /= 2;
            }
        }

        public void insert(int num) {
            heap.add(num);
            swim();
        }

        private void sink() {
            int pos = 1;
            int max;
            while ((max = 2 * pos) < size()) {
                if (max + 1 < size() && comparator.compare(heap.get(max + 1), heap.get(max)) > 0) {
                    max++;
                }

                if (comparator.compare(heap.get(max), heap.get(pos)) <= 0) {
                    return;
                }

                Collections.swap(heap, max, pos);
                pos = pos * 2 + max % 2;
            }
        }

        public int delTop() {
            int result = heap.get(1);
            Collections.swap(heap, 1, size() - 1);
            heap.remove(size() - 1);
            sink();
            return result;
        }

        public int size() {
            return heap.size();
        }

        public int top() {
            return size() == 1 ? -1 : heap.get(1);
        }
    }

    public static void fixSizes(Heap left, Heap right) {
        if (left.size() - right.size() == 2) {
            right.insert(left.delTop());
        }
        if (right.size() - left.size() == 2) {
            left.insert(right.delTop());
        }

        int result;
        if ((right.size() + left.size()) % 2 == 1) {
            result = right.size() > left.size() ? right.top() : left.top();
        } else {
            result = (left.top() + right.top()) / 2;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        Heap max = new Heap(Comparator.naturalOrder());
        Heap min = new Heap(Comparator.reverseOrder());
        int num;
        String str;

        while ((str = scanner.next()) != null) {
            num = Integer.parseInt(str);
            if (num < min.top()) {
                max.insert(num);
            } else {
                min.insert(num);
            }

            fixSizes(max, min);
        }
    }

}
