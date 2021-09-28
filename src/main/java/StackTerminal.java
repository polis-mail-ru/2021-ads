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
public final class StackTerminal {
    private void StackTerminal() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        String string = "";
        while (!string.equals("exit")) {
            string = in.next();
            switch (string) {
                case "push": {
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                }
                case "pop": {
                    if (stack.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.pop());
                    break;
                }
                case "back": {
                    if (stack.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.peek());
                    break;
                }
                case "size": {
                    out.println(stack.size());
                    break;
                }
                case "clear": {
                    stack.clear();
                    out.println("ok");
                    break;
                }
                case "exit": {
                    out.println("bye");
                    return;
                }
                default: {
                    out.println("error");
                    return;
                }
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