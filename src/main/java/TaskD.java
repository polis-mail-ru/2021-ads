import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskD {
    private TaskD() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String subseq = in.next();
        MyStack openBrackets = new MyStack();
        for (Character sym : subseq.toCharArray()) {
            if (sym.equals('(') || sym.equals('[') || sym.equals('{')) {
                openBrackets.push(sym);
            } else {
                Character lastSubseq = ' ';
                if (openBrackets.size() > 0)
                    lastSubseq = openBrackets.pop();
                else {
                    out.println("no");
                    return;
                }

                if (lastSubseq.equals('(') && !sym.equals(')')) {
                    out.println("no");
                    return;
                } else if (lastSubseq.equals('[') && !sym.equals(']')) {
                    out.println("no");
                    return;
                } else if (lastSubseq.equals('{') && !sym.equals('}')) {
                    out.println("no");
                    return;
                }
            }
        }
        if (openBrackets.size() == 0)
            out.println("yes");
        else
            out.println("no");
    }

    private static class MyStack {
        private ArrayList<Character> elems;

        public MyStack() {
            elems = new ArrayList<Character>();
        }

        public void push(Character elem) {
            elems.add(elem);
        }

        public Character pop() {
            Character res = elems.get(elems.size() - 1);
            elems.remove(elems.size() - 1);
            return res;
        }

        public Character back() {
            return elems.get(elems.size() - 1);
        }

        public int size() {
            return elems.size();
        }

        public void clear() {
            elems.clear();
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

