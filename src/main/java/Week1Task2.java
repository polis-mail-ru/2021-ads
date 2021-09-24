import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Задача №58. Очередь с защитой от ошибок
 */
public class Week1Task2 {
    private Week1Task2() {
        // Should not be instantiated
    }

    private static class Queue {
        Node first = null;
        Node last = null;
        int size = 0;

        private static class Node {
            public int data;
            public Node next;
            public Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
        }

        // Добавить в очередь число n
        public void push(int n) {
            last = new Node(n, last);
            if (first == null) {
                first = last;
            }
            size++;
        }
        // Удалить из очереди первый элемент
        public int pop() {
            int data = first.data;
            first = first.next;
            if (first == null) {
                last = null;
            }
            size--;
            return data;
        }
        // Значение первого элемента, не удаляя его из очереди
        public int front() {
            return first.data;
        }
        // количество элементов в очереди
        public int size() {
            return size;
        }
        // очистить очередь
        public void clear() {
            first = last = null;
            size = 0;
        }
        // проверить очередь на пустоту
        public boolean empty() {
            return size == 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Перед исполнением операций front и pop программа должна проверять, содержится ли в очереди хотя бы один элемент. Если во входных данных встречается операция front или pop, и при этом очередь пуста, то программа должна вместо числового значения вывести строку error.
        Queue queue = new Queue();
        while (true) {
            String operation = in.next();
            switch (operation) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.empty()) {
                        out.println("error");
                    } else {
                        out.println(queue.pop());
                    }
                    break;
                case "front":
                    if (queue.empty()) {
                        out.println("error");
                    } else {
                        out.println(queue.front());
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
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