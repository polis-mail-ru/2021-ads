import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TaskB {
    private TaskB() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        cmdHandler(in, out);
        out.println("bye");
    }

    private static void cmdHandler(final FastScanner in, final PrintWriter out) {
        String cmd = in.next();
        MyDeque deque = new MyDeque();
        while (!cmd.equals("exit")) {
            switch (cmd) {
                case "push":
                    deque.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (deque.size() > 0)
                        out.println(deque.pop());
                    else
                        out.println("error");
                    break;
                case "front":
                    if (deque.size() > 0)
                        out.println(deque.front());
                    else
                        out.println("error");
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
            }
            cmd = in.next();
        }
    }

    private static class MyDeque {
        private LinkedList<Integer> elems;

        public MyDeque() {
            elems = new LinkedList<Integer>();
        }

        public void push(int elem) {
            elems.add(elem);
        }

        public int pop() {
            int res = elems.get(0);
            elems.remove(0);
            return res;
        }

        public int front() {
            return elems.get(0);
        }

        public int size() {
            return elems.size();
        }

        public void clear() {
            elems.clear();
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
