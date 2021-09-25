import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №55. Стек с защитой от ошибок
 */
public final class Week1Task3 {
    private Week1Task3() {
        // Should not be instantiated
    }

    private static class Stack {
        Node top;
        int size;

        private static class Node {
            public int data;
            public Node next;
            public Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
        }

        // Добавить в стек число n
        void push(int n) {
            top = new Node(n, top);
            size++;
        }
        // Удалить из стека последний элемент
        int pop() {
            int data = top.data;
            top = top.next;
            size--;
            return data;
        }
        // значение последнего элемента
        int back() {
            return top.data;
        }
        // количество элементов в стеке
        int size() {
            return size;
        }
        //очистить стек
        void clear() {
            top = null;
            size = 0;
        }
        // пуст ли стек
        boolean empty() {
            return size == 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Перед исполнением операций back и pop программа должна проверять, содержится ли в стеке хотя бы один элемент. Если во входных данных встречается операция back или pop, и при этом стек пуст, то программа должна вместо числового значения вывести строку error.
        Stack stack = new Stack();
        while (true) {
            String operation = in.next();
            switch (operation) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.empty()) {
                        out.println("error");
                    } else {
                        out.println(stack.pop());
                    }
                    break;
                case "back":
                    if (stack.empty()) {
                        out.println("error");
                    } else {
                        out.println(stack.back());
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
                    throw new UnsupportedOperationException("operation '" + operation + "' is not supported");
            }
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
