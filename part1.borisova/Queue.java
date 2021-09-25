import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Queue {
    static final PrintWriter pw = new PrintWriter(System.out);
    static LinkedList<Integer> q = new LinkedList<>();

    private static void pop() {
        pw.println(q.isEmpty() ? "error" : q.pollFirst());
    }
    private static void front() {
        pw.println(q.isEmpty() ? "error" : q.peekFirst());
    }

    private static void solve(final FastScanner in) {
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
                case "front":
                    front();
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