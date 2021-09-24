import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Task2 {
    private Week1Task2() {
        // Should not be instantiated
    }

    private static LinkedList<Integer> queue = new LinkedList<Integer>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        final String PUSH = "push";
        final String POP = "pop";
        final String FRONT = "front";
        final String SIZE = "size";
        final String CLEAR = "clear";
        final String EXIT = "exit";

        String command = "";
        do {
            command = in.next();

            switch (command) {
                case PUSH: {
                    int number = in.nextInt();
                    queue.addFirst(number);
                    out.println("ok");
                    break;
                }
                case POP: {
                    if (!queue.isEmpty()) {
                        out.println(queue.getLast());
                        queue.removeLast();
                    } else {
                        out.println("error");
                    }
                    break;
                }
                case FRONT: {
                    if (!queue.isEmpty()) {
                        out.println(queue.getLast());
                    } else {
                        out.println("error");
                    }
                    break;
                }
                case SIZE: {
                    out.println(queue.size());
                    break;
                }
                case CLEAR: {
                    queue.clear();
                    out.println("ok");
                    break;
                }
                case EXIT: {
                    out.println("bye");
                    break;
                }
            }
        } while(!command.equals("exit"));
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
