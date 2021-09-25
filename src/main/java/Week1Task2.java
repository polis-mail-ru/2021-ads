import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class Week1Task2 {
    private Week1Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue myQueue = new Queue();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push": {
                    int newNum = Integer.parseInt(in.next());
                    out.println(myQueue.Push(newNum));
                    break;
                }
                case "pop": out.println(myQueue.Pop()); break;
                case "front": out.println(myQueue.Front()); break;
                case "size": out.println(myQueue.Size()); break;
                case "clear": out.println(myQueue.Clear()); break;
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

class Queue {
    private LinkedList<Integer> queue;

    public Queue() {
        queue = new LinkedList<Integer>();
    }

    public String Push(int n) {
        queue.push(n);
        return "ok";
    }

    public String Pop() {
        if (queue.size() != 0)
        {
            return String.valueOf(queue.removeLast());
        }
        return "error";
    }

    public String Front() {
        if (queue.size() != 0) {
            return String.valueOf(queue.getLast());
        }
        return "error";
    }

    public String Size() {
        return String.valueOf(queue.size());
    }

    public String Clear() {
        queue.clear();
        return "ok";
    }
}
