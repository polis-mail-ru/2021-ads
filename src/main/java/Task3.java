import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();

        while (true) {
            String instruction = in.next();

            if (instruction == null) {
                return;
            }

            switch (instruction) {
                case ("push"):
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case ("pop"):
                    if (stack.size() != 0)
                        out.println(stack.pop());
                    else
                        out.println("error");
                    break;
                case ("back"):
                    if (stack.size() != 0)
                        out.println(stack.back());
                    else
                        out.println("error");
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
                    return;
            }
        }
    }

    private static class Stack {
        LinkedList<Object> stack;

        private Stack() {
            this.stack = new LinkedList<>();
        }

        void push(Object n) {
            stack.addFirst(n);
        }

        Object pop() {
            return stack.pollFirst();
        }

        Object back() {
            return stack.getFirst();
        }

        int size () {
            return stack.size();
        }

        void clear() {
            stack = new LinkedList<>();
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
