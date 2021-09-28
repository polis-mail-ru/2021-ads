import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// B. Очередь с защитой от ошибок
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    public static class Queue {
        private final LinkedList<Integer> data;

        public Queue() {
            this.data = new LinkedList<>();
        }

        private void push(int n) {
            data.addLast(n);
            System.out.println("ok");
        }

        private void pop() {
            if (data.isEmpty()) {
                System.out.println("error");
                return;
            }
            System.out.println(data.getFirst());
            data.removeFirst();
        }

        private void front() {
            if (data.isEmpty()) {
                System.out.println("error");
                return;
            }
            System.out.println(data.getFirst());
        }

        private void size() {
            System.out.println(data.size());
        }

        private void clear() {
            data.clear();
            System.out.println("ok");
        }

        public void exec(String command) {
            switch (command) {
                case "pop" -> pop();
                case "front" -> front();
                case "size" -> size();
                case "clear" -> clear();
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String s = in.next();
        while (!s.equals("exit")) {
            if (s.equals("push")) {
                queue.push(in.nextInt());
            } else {
                queue.exec(s);
            }
            s = in.next();
        }
        System.out.println("bye");
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