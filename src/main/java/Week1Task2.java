import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Task2 {
    private Week1Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> queue = new LinkedList<>();
        String command = in.next();
        while (true) {
            if (command.equals("push")) {
                queue.add(in.nextInt());
                out.println("ok");
            }
            if (command.equals("pop") || command.equals("front")) {
                if (queue.isEmpty()) {
                    out.println("error");
                } else {
                    out.println(queue.peek());
                    if (command.equals("pop")) queue.removeFirst();
                }
            }
            if (command.equals("size")) {
                out.println(queue.size());
            }
            if (command.equals("clear")) {
                queue.clear();
                out.println("ok");
            }
            if (command.equals("exit")) {
                out.println("bye");
                return;
            }
            command = in.next();
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
