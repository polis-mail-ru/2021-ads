import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Stack {

    static final PrintWriter pw = new PrintWriter(System.out);
    static LinkedList<Integer> q = new LinkedList<>();

    private static void pop() {
        pw.println(q.isEmpty() ? "error" : q.pollLast());
    }
    private static void back() {
        pw.println(q.isEmpty() ? "error" : q.peekLast());
    }

    private static void solve(final Stack.FastScanner in) {
        mainLoop:
        while (true) {
            switch (in.next()) {
                case "push":
                    q.add(in.nextInt());
                    pw.println("ok");
                    break;
                case "pop":
                    pop();
                    break;
                case "back":
                    back();
                    break;
                case "size":
                    pw.println(q.size());
                    break;
                case "clear":
                    q.clear();
                    pw.println("ok");
                    break;
                case ("exit"):
                    pw.println("bye");
                    break mainLoop;
            }
        }
        pw.flush();
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        solve(in);
    }
}
