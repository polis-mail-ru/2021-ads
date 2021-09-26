import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        String expression = in.nextLine();
        for (String arg : expression.split(" ")) {
            switch (arg) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-1 * (stack.pop() - stack.pop()));
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(arg));
            }
        }
        out.println(stack.pop());
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

