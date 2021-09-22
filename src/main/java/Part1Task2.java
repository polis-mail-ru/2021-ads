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
public final class Part1Task2 {
    private Part1Task2() {
        // Should not be instantiated
    }

    public static final String MESSAGE_OK = "ok";
    public static final String MESSAGE_ERROR = "error";
    public static final String MESSAGE_BYE = "bye";

    public static final String COMMAND_PUSH = "push";
    public static final String COMMAND_POP = "pop";
    public static final String COMMAND_FRONT = "front";
    public static final String COMMAND_SIZE = "size";
    public static final String COMMAND_CLEAR = "clear";
    public static final String COMMAND_EXIT = "exit";

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int size = 0;

        boolean isContinue = true;
        String token;
        while (isContinue) {
            token = in.next();
            switch (token) {
                case COMMAND_PUSH:
                    list.offer(in.nextInt());
                    out.println(MESSAGE_OK);
                    size++;
                    break;
                case COMMAND_POP:
                    if (size == 0) {
                        out.println(MESSAGE_ERROR);
                    } else {
                        out.println(list.poll());
                        size--;
                    }
                    break;
                case COMMAND_FRONT:
                    if (size == 0) {
                        out.println(MESSAGE_ERROR);
                    } else {
                        out.println(list.peek());
                    }
                    break;
                case COMMAND_SIZE:
                    out.println(size);
                    break;
                case COMMAND_CLEAR:
                    list.clear();
                    out.println(MESSAGE_OK);
                    size = 0;
                    break;
                case COMMAND_EXIT:
                    out.println(MESSAGE_BYE);
                    isContinue = false;
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
