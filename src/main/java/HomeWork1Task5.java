import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;


public final class HomeWork1Task5 {
    private HomeWork1Task5() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        List<Integer> list = new ArrayList<>();
        Map<String, BinaryOperator<Integer>> operators = new HashMap<>();
        operators.put("+", (x1, x2) -> x1 + x2);
        operators.put("-", (x1, x2) -> x1 - x2);
        operators.put("*", (x1, x2) -> x1 * x2);
        do {
            String readString = in.next();
            if (operators.containsKey(readString)) {
                int operand1 = list.remove(list.size() - 2);
                int operand2 = list.remove(list.size() - 1);
                int result = operators.get(readString).apply(operand1, operand2);
                list.add(result);
            } else {
                list.add(Integer.parseInt(readString));
            }
        } while (in.hasNext());
        out.println(list.get(0));
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

        boolean hasNext() {
            if (tokenizer == null) {
                return true;
            }
            return tokenizer.hasMoreTokens();
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