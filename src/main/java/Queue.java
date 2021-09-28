
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
public final class Queue {
    private Queue() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        String string = "";
        do {
            string = in.next();
            switch (string) {
                case "push": {
                    queue.add(in.nextInt());
                    out.println("ok");
                    break;
                }
                case "pop": {
                    if (queue.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.remove());
                    break;
                }
                case "front": {
                    if (queue.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.peek());
                    break;
                }
                case "size": {
                    out.println(queue.size());
                    break;
                }
                case "clear": {
                    queue.clear();
                    out.println("ok");
                    break;
                }
                case "exit": {
                    out.println("bye");
                    break;
                }
                default: {
                    out.println("error");
                    return;
                }
            }
        } while (!string.equals("exit"));
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