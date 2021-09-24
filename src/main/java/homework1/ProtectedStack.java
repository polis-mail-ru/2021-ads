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
public final class ProtectedStack {
    private ProtectedStack() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String line = "";
        Stack stack = new Stack();
        while (!line.equals("exit")) {
            line = in.next();
            switch (line) {
                case ("push"):
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case ("pop"):
                    if(stack.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.pop());
                    break;
                case ("back"):
                    if(stack.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.back());
                    break;
                case ("size"):
                    out.println(stack.size());
                    break;
                case ("clear"):
                    stack.clear();
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

    public static class Stack {
        private final LinkedList<Integer> storage = new LinkedList<Integer>();

        public void push(int a) {
            storage.addLast(a);
        }

        public int pop() {
            return storage.removeLast();
        }

        public int back() {
            return storage.getLast();
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
