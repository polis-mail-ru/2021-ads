package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class ProtectedQueue {
    private ProtectedQueue() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String line = "";
        Queue queue = new Queue();
        while (!line.equals("exit")) {
            line = in.next();
            switch (line) {
                case ("push"):
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case ("pop"):
                    if(queue.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.pop());
                    break;
                case ("front"):
                    if(queue.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.front());
                    break;
                case ("size"):
                    out.println(queue.size());
                    break;
                case ("clear"):
                    queue.clear();
                    out.println("ok");
                    break;
                case ("exit"):
                    out.println("bye");
                    break;
                default:
                    break;
            }
        }
    }

    public static class Queue {
        private final LinkedList<Integer> storage = new LinkedList<Integer>();

        public void push(int a) {
            storage.addLast(a);
        }

        public int pop() {
            return storage.removeFirst();
        }

        public int front() {
            return storage.getFirst();
        }

        public int size() {
            return storage.size();
        }

        public void clear() {
            storage.clear();
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
