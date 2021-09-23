import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue<Integer> numbersQueue = new Queue<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        for (int i = 0; i < input.length(); i++) {
            char tmpChar = input.charAt(i);
            if (Character.isSpaceChar(tmpChar)) {
                continue;
            }

            if (tmpChar == '+') {
                numbersQueue.push(numbersQueue.popLast() + numbersQueue.popLast());
            } else if (tmpChar == '-') {
                numbersQueue.push(- (numbersQueue.popLast() - numbersQueue.popLast()));
            } else if (tmpChar == '*') {
                numbersQueue.push(numbersQueue.popLast() * numbersQueue.popLast());
            } else if (Character.isDigit(tmpChar)) {
                numbersQueue.push(Character.getNumericValue(tmpChar));
            }
        }
        out.println(numbersQueue.pop());
    }

    private static class Queue<T> {
        LinkedList<T> queue;

        private Queue() {
            this.queue = new LinkedList<>();
        }

        void push(T n) {
            queue.addLast(n);
        }

        T pop() {
            return queue.pollFirst();
        }

        T popLast() {
            return queue.pollLast();
        }

        T front() {
            return queue.getFirst();
        }

        int size () {
            return queue.size();
        }

        void clear() {
            queue = new LinkedList<>();
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
