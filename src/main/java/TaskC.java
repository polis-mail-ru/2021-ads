import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskC {
    private TaskC() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        cmdHandler(in, out);
        out.println("bye");
    }

    private static void cmdHandler(final FastScanner in, final PrintWriter out) {
        String cmd = in.next();
        MyStack stack = new MyStack();
        while (!cmd.equals("exit")) {
            switch (cmd) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.size() > 0)
                        out.println(stack.pop());
                    else
                        out.println("error");
                    break;
                case "back":
                    if (stack.size() > 0)
                        out.println(stack.back());
                    else
                        out.println("error");
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
            }
            cmd = in.next();
        }
    }

    private static class MyStack {
        private ArrayList<Integer> elems;

        public MyStack() {
            elems = new ArrayList<Integer>();
        }

        public void push(int elem) {
            elems.add(elem);
        }

        public int pop() {
            int res = elems.get(elems.size() - 1);
            elems.remove(elems.size() - 1);
            return res;
        }

        public int back() {
            return elems.get(elems.size() - 1);
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


