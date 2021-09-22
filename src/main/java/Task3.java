import java.io.*;
import java.util.*;

public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final Task3.FastScanner in, final PrintWriter out) {
        Deque<Integer> data = new LinkedList<>();
        String input;
        boolean active = true;
        while (active) {
            input = in.next();
            switch (input) {
                case "push":
                    data.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (data.isEmpty()) {
                        out.println("error");
                    } else {
                        out.println(data.pop());
                    }
                    break;
                case "back":
                    if (data.isEmpty()) {
                        out.println("error");
                    } else {
                        out.println(data.peek());
                    }
                    break;
                case "size":
                    out.println(data.size());
                    break;
                case "clear":
                    data.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    active = false;
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
        final Task3.FastScanner in = new Task3.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
