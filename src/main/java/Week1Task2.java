import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №58. Очередь с защитой от ошибок
 */
public class Week1Task2 {
    private Week1Task2() {
        // Should not be instantiated
    }

    // Queue implemented using single-linked list
    private static class Queue {
        private Node first = null;
        private Node last = null;
        private int size = 0;

        private static class Node {
            int data;
            Node next;

            Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
        }

        // push the element to the end
        public void push(int n) {
            if (last == null) {
                first = new Node(n, null);
                last = first;
            } else {
                last.next = new Node(n, null);
                last = last.next;
            }
            size++;
        }

        // pop the front element
        public int pop() {
            int data = first.data;
            first = first.next;
            if (first == null) {
                last = null;
            }
            size--;
            return data;
        }

        // get the front element
        public int front() {
            return first.data;
        }

        public int size() {
            return size;
        }

        public void clear() {
            first = null;
            last = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        while (true) {
            String operation = in.next();
            switch (operation) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.pop());
                    break;
                case "front":
                    if (queue.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.front());
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
                    throw new UnsupportedOperationException("operation '" + operation + "' is not supported");
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}