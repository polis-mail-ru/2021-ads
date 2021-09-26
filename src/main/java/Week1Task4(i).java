import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


// D. Правильная скобочная последовательность
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    public static class Stack {
        private char[] data;
        private int last = 0;

        public Stack() {
            this.data = new char[1024];
        }

        private void doubleArray() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        public void push(char c) {
            if (data.length <= last) {
                doubleArray();
            }
            data[last] = c;
            last++;
        }

        public int pop() {
            if (last == 0) {
                return 0;
            }
            last--;
            data[last] = 0;
            return data[last];
        }

        public int back() {
            if (last == 0) {
                return 0;
            }
            return data[last - 1];
        }

        public int size() {
            return last;
        }

        public void clear() {
            Arrays.fill(data, (char) 0);
            last = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String s = in.next();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    System.out.println("no");
                    return;
                }
                if ((c == ']' && stack.back() == '[') || (c == ')' && stack.back() == '(') || (c == '}' && stack.back() == '{')) {
                    stack.pop();
                } else {
                    System.out.println("no");
                    return;
                }
            }
        }
        if (stack.size() == 0) {
            System.out.println("yes");
            return;
        }
        System.out.println("no");
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