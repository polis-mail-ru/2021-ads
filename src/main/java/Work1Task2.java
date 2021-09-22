import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work1Task2 {
    private Work1Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        while (true) {
            String command = in.next();

            switch (command) {
                case "push": {
                    Queue.push(in.nextInt());
                    out.println("ok");
                    break;
                }
                case "pop": {
                    if (Queue.getSize() > 0) out.println(Queue.pop());
                    else out.println("error");
                    break;
                }
                case "front": {
                    if (Queue.getSize() > 0) out.println(Queue.front());
                    else out.println("error");
                    break;
                }
                case "size": {
                    out.println(Queue.getSize());
                    break;
                }
                case "clear": {
                    Queue.clear();
                    out.println("ok");
                    break;
                }
                case "exit": {
                    out.println("bye");
                    return;
                }
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

    private static class Queue {
        private static int size = 0;
        private static Node first = null;
        private static Node last = null;

        private static class Node {
            public Node next = null;
            public int value;

            public Node(int value) {
                this.value = value;
            }
        }

        public static void push(int n) {
            Node node = new Node(n);
            if (last == null) {
                last = node;
                first = node;
            } else {
                last.next = node;
                last = node;
            }
            size++;
        }

        public static int pop() {
            if (size == 1) last = null;
            int value = first.value;
            first = first.next;
            size--;
            return value;
        }

        public static int front() {
            return first.value;
        }

        public static int getSize() {
            return size;
        }

        public static void clear(){
            first = null;
            last = null;
            size = 0;
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
