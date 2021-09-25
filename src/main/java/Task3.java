import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
    }

    private static class Stack {
        private final List<Integer> list;

        public Stack() {
            this.list = new ArrayList<>();
        }

        public void push(int n) {
            list.add(n);
        }

        public int pop() {
            int index = list.size() - 1;
            int res = list.get(index);
            list.remove(index);
            return res;
        }

        public int back() {
            return list.get(list.size() - 1);
        }

        public int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                case "back":
                    if (stack.size() != 0) {
                        if (command.equals("pop")) {
                            out.println(stack.pop());
                        } else {
                            out.println(stack.back());
                        }
                    } else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                default:
                    break;
            }
            command = in.next();
        }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
