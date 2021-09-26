import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// Простая очередь
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    public static class Queue {
        private int[] data;
        private int first = 0;
        private int last = 0;

        public Queue() {
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
            if (last - first == 0) {
                System.out.println("error");
                return;
            }
            System.out.println(data[first]);
            first++;
        }

        private void front() {
            if (last - first == 0) {
                System.out.println("error");
                return;
            }
            System.out.println(data[first]);
        }

        private void size() {
            System.out.println(last - first);
        }

        private void clear() {
            first = last;
            System.out.println("ok");
        }

        public void exec(String command) {
            switch (command) {
                case "pop":
                    pop();
                    break;
                case "front":
                    front();
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
        Queue queue = new Queue();
        String s = in.next();
        while (!s.equals("exit")) {
            if (s.equals("push")) {
                queue.push(Integer.parseInt(in.next()));
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