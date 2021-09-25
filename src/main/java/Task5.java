import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static class Stack {
        private final List<Integer> list;

        public Stack() {
            this.list = new ArrayList<>();
        }

        public void push(int n) {
            list.add(n);
        }

        public int pop() {
            int index = list.size() - 1;
            int res = list.get(index);
            list.remove(index);
            return res;
        }

        public int back() {
            return list.get(list.size() - 1);
        }

        public int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        stack.push(in.nextInt());
        stack.push(in.nextInt());
        String nextSymbol = in.next();
        int counter = in.getCountOfTokens();
        for (int i = 0; i <= counter; i++) {
            switch (nextSymbol) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first - second);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(nextSymbol));
                    break;
            }
            if (i != counter) {
                nextSymbol = in.next();
            }

        }
        out.println(stack.pop());
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

        int getCountOfTokens() {
            return tokenizer.countTokens();
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
