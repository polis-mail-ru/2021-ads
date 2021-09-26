import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack stack = new MyStack();
        String command;
        do {
            command = in.next();
            switch (command) {
                case "push":
                    out.println(stack.push(in.nextInt()));
                    break;
                case "pop":
                    out.println(stack.pop());
                    break;
                case "back":
                    out.println(stack.back());
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    out.println(stack.clear());
                    break;
                default:
            }
        } while (!command.equals("exit"));
        out.println("bye");
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

    private static class MyStack {
        private final Deque<Integer> stack = new LinkedList<>();

        String push(int n) {
            stack.push(n);
            return "ok";
        }

        String pop() {
            if (size() == 0)
                return "error";
            return stack.pop().toString();
        }

        String back() {
            if (size() == 0)
                return "error";
            return stack.peek().toString();
        }

        int size() {
            return stack.size();
        }

        String clear() {
            stack.clear();
            return "ok";
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
