import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

        public void doubleArray() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        public void push(int n) {
            if (data.length <= last) {
                doubleArray();
            }
            data[last] = n;
            last++;
        }

        public int pop() {
            if (last == 0) {
                return 0;
            }
            int a = data[--last];
            data[last] = 0;
            return a;
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
            Arrays.fill(data, 0);
            last = 0;
        }
    }

    private static int getAnswerBySignOperation(String sign, int a, int b) {
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> 0;
        };
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        Set<String> set = new HashSet<>(Arrays.asList("+", "-", "*"));

        Scanner scanner = new Scanner(System.in);

        String[] s = scanner.nextLine().trim().split(" ");

        for (String el : s) {
            if (set.contains(el)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(getAnswerBySignOperation(el, b, a));
            } else {
                stack.push(Integer.parseInt(el));
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