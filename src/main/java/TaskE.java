import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskE {
    private TaskE() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack digitStack = new MyStack();
        do {
            String mathSym = in.next();
            if (isDigit(mathSym)) {
                digitStack.push(Integer.parseInt(mathSym));
            } else if (isOper(mathSym)) {
                int operand2 = digitStack.pop();
                int operand1 = digitStack.pop();
                int res = makeCompution(operand1, operand2, mathSym);
                digitStack.push(res);
            }
        } while (in.isNext());
        int answer = digitStack.pop();
        out.println(answer);
    }

    private static int makeCompution(int operand1, int operand2, String mathSym) {
        switch (mathSym) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
        }
        return Integer.MAX_VALUE;
    }

    private static boolean isOper(String mathSym) {
        if (mathSym.equals("+") || mathSym.equals("-") || mathSym.equals("*"))
            return true;
        return false;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static class MyStack {
        private ArrayList<Integer> elems;

        public MyStack() {
            elems = new ArrayList<Integer>();
        }

        public void push(int elem) {
            elems.add(elem);
        }

        public int pop() {
            int res = elems.get(elems.size() - 1);
            elems.remove(elems.size() - 1);
            return res;
        }

        public int back() {
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

        boolean isNext() {
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
