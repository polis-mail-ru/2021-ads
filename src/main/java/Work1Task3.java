import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work1Task3 {
    private Work1Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        while (true) {
            String command = in.next();

            switch (command) {
                case "push": {
                    Stack.push(in.nextInt());
                    out.println("ok");
                    break;
                }
                case "pop": {
                    if (Stack.getSize() > 0) out.println(Stack.pop());
                    else out.println("error");
                    break;
                }
                case "back": {
                    if (Stack.getSize() > 0) out.println(Stack.back());
                    else out.println("error");
                    break;
                }
                case "size": {
                    out.println(Stack.getSize());
                    break;
                }
                case "clear": {
                    Stack.clear();
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

    private static class Stack {
        private static int size = 0;
        private static Node last = null;

        private static class Node {
            public Node prev = null;
            public int value;

            public Node(int value) {
                this.value = value;
            }
        }

        public static void push(int n) {
            Node node = new Node(n);
            if (last != null) {
                node.prev = last;
            }
            last = node;
            size++;
        }

        public static int pop() {
            int value = last.value;
            last = last.prev;
            size--;
            return value;
        }

        public static int back() {
            return last.value;
        }

        public static int getSize() {
            return size;
        }

        public static void clear(){
            last = null;
            size = 0;
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
