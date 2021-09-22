import java.io.*;
import java.util.*;
import java.util.function.Supplier;

import static com.google.common.primitives.Chars.asList;


public final class HomeWork1Task3 {
    private HomeWork1Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String bracketsSequence = in.next();
        List<Character> list = new ArrayList<>();
        Map<Character, Character> brackets = Map.of('(', ')', '[', ']', '{', '}');
        for (char bracket : bracketsSequence.toCharArray()) {
            if (brackets.containsKey(bracket)) {
                list.add(bracket);
            } else if (bracket == brackets.get(list.get(list.size() - 1))){
                list.remove(list.size() - 1);
            } else {
                out.println("no");
                return;
            }
        }
        if (list.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
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