import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class Week1Task3 {
    private Week1Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack myStack = new Stack();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push": {
                    int newNum = Integer.parseInt(in.next());
                    out.println(myStack.Push(newNum));
                    break;
                }
                case "pop": out.println(myStack.Pop()); break;
                case "back": out.println(myStack.Back()); break;
                case "size": out.println(myStack.Size()); break;
                case "clear": out.println(myStack.Clear()); break;
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

class Stack {
    private LinkedList<Integer> stack;

    public Stack() {
        stack = new LinkedList<Integer>();
    }

    public String Push(int n) {
        stack.push(n);
        return "ok";
    }

    public String Pop() {
        if (stack.size() != 0)
        {
            return String.valueOf(stack.removeFirst());
        }
        return "error";
    }

    public String Back() {
        if (stack.size() != 0) {
            return String.valueOf(stack.getFirst());
        }
        return "error";
    }

    public String Size() {
        return String.valueOf(stack.size());
    }

    public String Clear() {
        stack.clear();
        return "ok";
    }
}
