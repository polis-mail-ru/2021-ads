import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        //Queue<Integer> queue = new Queue<>();
        LinkedList<Integer> queue = new LinkedList<>();
        while (true) {
            String s = in.next();

            if (s.startsWith("pu")) {
                out.println("ok");
                queue.add(in.nextInt());

                //queue.push(in.nextInt());
            }
            if (s.startsWith("po")) {
                if (queue.size() == 0) {
                    out.println("error");
                    continue;
                }
                out.println(queue.get(0));
                queue.remove(0);
            }
            if (s.startsWith("f")) {
                if (queue.size() == 0) {
                    out.println("error");
                    continue;
                }
                out.println(queue.get(0));
            }
            if (s.startsWith("s")) {
                out.println(queue.size());
            }
            if (s.startsWith("c")) {
                out.println("ok");
                queue.clear();
            }
            if (s.startsWith("e")) {
                out.println("bye");
                break;
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


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}