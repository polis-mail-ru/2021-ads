import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        //Queue<Integer> queue = new Queue<>();
        LinkedList<Integer> stack = new LinkedList<>();
        while (true) {
            String s = in.next();
            if (s.startsWith("push")) {
                System.out.println("ok");
                stack.addLast(in.nextInt());
            }
            if (s.startsWith("pop")) {
                if (stack.size() == 0) {
                    System.out.println("error");
                    continue;
                }
                System.out.println(stack.pollLast());
            }
            if (s.startsWith("back")) {
                if (stack.size() == 0) {
                    System.out.println("error");
                    continue;
                }
                System.out.println(stack.peekLast());
            }
            if (s.startsWith("size")) {
                System.out.println(stack.size());
            }
            if (s.startsWith("clear")) {
                System.out.println("ok");
                stack.clear();
            }
            if (s.startsWith("exit")) {
                System.out.println("bye");
                break;
            }
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
