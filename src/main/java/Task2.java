import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();

        while (true) {
            String instruction = in.next();

            if (instruction == null) {
                return;
            }

            switch (instruction) {
                case ("push"):
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case ("pop"):
                    if (queue.size() != 0)
                        out.println(queue.pop());
                    else
                        out.println("error");
                    break;
                case ("front"):
                    if (queue.size() != 0)
                        out.println(queue.front());
                    else
                        out.println("error");
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
                    return;
            }
        }
    }

    private static class Queue {
        LinkedList<Object> queue;

        private Queue() {
            this.queue = new LinkedList<>();
        }

        void push(int n) {
            queue.addLast(n);
        }

        Object pop() {
            return queue.pollFirst();
        }

        Object front() {
            return queue.getFirst();
        }

        int size () {
            return queue.size();
        }

        void clear() {
            queue = new LinkedList<>();
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
