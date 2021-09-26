import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Стек неограниченного размера
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    public static class Stack {
        private int[] data;
        private int last = 0;

        public Stack() {
            this.data = new int[1024];
        }

        private void doubleArray() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        private void push(int n) {
            if (data.length <= last) {
                doubleArray();
            }
            data[last] = n;
            last++;
            System.out.println("ok");
        }

        private void pop() {
            if (last == 0) {
                System.out.println("error");
                return;
            }
            System.out.println(data[--last]);
            data[last] = 0;
        }

        private void back() {
            if (last == 0) {
                System.out.println("error");
                return;
            }
            System.out.println(data[last - 1]);
        }

        private void size() {
            System.out.println(last);
        }

        private void clear() {
            Arrays.fill(data, 0);
            last = 0;
            System.out.println("ok");
        }

        public void exec(String command) {
            switch (command) {
                case "pop":
                    pop();
                    break;
                case "back":
                    back();
                    break;
                case "size":
                    size();
                    break;
                case "clear":
                    clear();
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String s = in.next();
        while (!s.equals("exit")) {
            if (s.equals("push")) {
                stack.push(in.nextInt());
            } else {
                stack.exec(s);
            }
            s = in.next();
        }
        out.println("bye");
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
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }
}