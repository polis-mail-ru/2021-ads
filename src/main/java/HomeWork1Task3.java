import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Supplier;


public final class HomeWork1Task3 {
    private HomeWork1Task3() {
        // Should not be instantiated
    }

    private static ArrayList<Integer> list = new ArrayList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<String, Supplier> commands = new HashMap<>();
        commands.put("push", () -> {
            int number = in.nextInt();
            list.add(number);
            return "ok";
        });
        commands.put("size", HomeWork1Task3::size);
        commands.put("pop", HomeWork1Task3::pop);
        commands.put("back", HomeWork1Task3::back);
        commands.put("clear", HomeWork1Task3::clear);
        commands.put("exit", HomeWork1Task3::exit);

        String command = "";
        while (!command.equals("exit")) {
            command = in.next();
            out.println(commands.get(command).get());
        }
    }

    private static int size() {
        return list.size();
    }
    private static String pop() {
        if (list.isEmpty()) {
            return "error";
        }
        return list.remove(size() - 1).toString();
    }
    private static String back() {
        if (list.isEmpty()) {
            return "error";
        }
        return list.get(size() - 1).toString();
    }
    private static String clear() {
        list.clear();
        return "ok";
    }
    private static String exit() {
        return "bye";
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